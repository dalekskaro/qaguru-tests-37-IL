## Урок 9: Работа с файлами
[9. Работаем с файлами. Дмитрий Тучс](https://school.qa.guru/pl/teach/control/lesson/view?id=343208849&editMode=0)
1. Как скачать и прочитать файл
2. Проверяем контент в .txt, .pdf, .xls/.xlsx, .doc/.docx, .zip

Класс `File` — абстракция над путем к файлу в папке в памяти машины. Для чтения/записи существует понятие `InputStream` и `OutputStream` соответственно. Для чтения необходимо создать новый `InputStream` и передать ему файл. Далее из файла надо получить массив байт и декодировать его в необходимый стандарт. По завершении поток надо обязательно закрыть.

Для работы с файлами, будь то чтение или запись, мы работаем со стримами и ридерами. Стримы и ридеры надо за собой закрывать, так как они работают с файлами-дескрипторами операционной системы.
Это закрытие происходит через вызов метода `close()`, который определен в интерфейсе `Closeable`, который является наследником `AutoCloseable` и вызов `close()` может проходить автоматически если используется `try с ресурсами`:
`try (InputStream is = new FileInputStream(downloaded)) {тут код, по которому читаем содержимое}`

Для того, чтобы читать что-то из `classPath` нам нужен механизм `ClassLoader`  
`ClassLoader` - специальный объект в Java, что отвечает за поиск и загрузку классов в `classPath` и файлов в ресурсы. Его можно получить находясь с любом классе в джаве. Получение ClassLoader:
```java
// Этот метод вернет classLoader в котором загружен этот класс
private ClassLoader classLoader = Наименование_класса.class.getClassLoader();
```

[Как скачать файл с помощью Selenide](https://ru.selenide.org/2019/12/10/advent-calendar-download-files/)  

#### Отладка, действие 'Evaluate'
Для отладки файлов в режиме дебага можно нажать на ПКМ - `Evaluate` и внутри ввести, например, `pdf.text`

#### PDF
[GitHub pdf-test](https://github.com/codeborne/pdf-test)  
Библиотека для чтения pdf-файлов:
```groovy
// https://mvnrepository.com/artifact/com.codeborne/pdf-test
implementation 'com.codeborne:pdf-test:1.8.1'
```
Статья "[Автоматизируем проверку содержимого PDF-файлов с помощью pdf-test](https://habr.com/ru/companies/simbirsoft/articles/794728/)"  
Для открытия PDF-файла необходимо использовать ClassLoader. Это поможет сохранить независимость от файловой системы. Далее необходимо создать новый класс PDF и передать поток.
```java
@Test
void pdfTest() throws Exception {
    InputStream stream = cl.getResourceAsStream("pdf/junit-user-guide-5.8.2.pdf");
        PDF pdf = new PDF(stream);
}
```

#### XLS
[GitHub xls-test](https://github.com/codeborne/xls-test)  
Библиотека для чтения excel-файлов:
```groovy
// https://mvnrepository.com/artifact/com.codeborne/xls-test
implementation 'com.codeborne:xls-test:1.7.2'
```
Решение проблемы с версиями с pdf-test и xls-test:
```groovy
    testImplementation(
            'com.codeborne:pdf-test:2.0.0',
            'org.apache.poi:poi:5.4.1',
            'org.apache.poi:poi-ooxml:5.4.1'
    )

    testImplementation ('com.codeborne:xls-test:1.7.2') {
                exclude(group: 'org.apache.poi', module: 'poi')
                exclude(group: 'org.apache.poi', module: 'poi-ooxml')
            }
```
Элементы таблицы:  
- листы — `getSheetAt();`
- строчки — `getRow();`
- столбцы — `getCell();`
- ячейка — пересечение строки и столбца.
```java
@Test
void xlsTest() throws Exception {
    InputStream stream = getClass().getClassLoader().getResourceAsStream("xls/file.xls");
    XLS xls = new XLS(stream);
 // Пример записи строки из первого листа, третьей строки и первого столбца в переменную (нумерация начинается с нуля):
    String value = xls.excel.getSheetAt(0).getRow(3).getCell(0).getStringCellValue();
}
```

#### CSV
Нужен `ClassLoader`
Библиотека для чтения csv-файлов:
```groovy
// https://mvnrepository.com/artifact/com.opencsv/opencsv
implementation 'com.opencsv:opencsv:5.12.0'
```
Код:
```java
@Test
    void csvTest() throws Exception {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("example.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            
            List<String[]> data = csvReader.readAll();
            Assertions.assertEquals(2, data.size());
            Assertions.assertArrayEquals(new String[] {"Selenide", "https://selenide.org"}, data.get(0));
            Assertions.assertThat(data).contains(new String[]{"---", "---"}, new String[]{"---", "---"});
        }
    }
```
**Пояснение кода:**
```java
try (InputStream stream = getClass().getClassLoader().getResourceAsStream("example.csv");
```
- `try-with-resources` — всё, что создано в круглых скобках, будет автоматически закрыто после выполнения блока.
- `getClass().getClassLoader()` — берёт загрузчик классов, который знает, где лежат ресурсы проекта.
- `getResourceAsStream("example.csv")` — открывает файл example.csv из папки resources и превращает его в поток байтов (`InputStream`).
```java
CSVReader csvReader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8)))
```
- Создаётся `CSVReader` — парсер CSV-файлов.
- Внутрь передаётся `InputStreamReader`, который превращает поток байтов в поток символов.
- Указывается кодировка `UTF-8`, чтобы правильно читать символы (особенно если в файле есть кириллица или специальные символы).
```java
List<String[]> data = csvReader.readAll();
```
- `readAll()` читает весь CSV-файл целиком и возвращает его как список массивов строк `List<String[]>`.
- Каждая строка CSV → один элемент списка.
- Каждая ячейка строки → элемент массива.
#### ZIP
Для работы с zip в Java уже есть библиотека: `java.util.zip`
```java
    @Test
void zipTest() throws Exception {
    ZipFile zipFile = new ZipFile(new File("src/test/resources/zip/sample-zip-file.zip"));
    ZipInputStream zipInputStream = new ZipInputStream(classLoader.getResourceAsStream("zip/sample-zip-file.zip"));
    ZipEntry entry;
    while((entry = zipInputStream.getNextEntry()) != null) {
        Assertions.assertEquals("sample.txt", entry.getName());
        try (InputStream inputStream = zipFile.getInputStream(entry)) {
            // проверки
        }
    }
}
```
**Разбор кода:**
```java
ZipFile zipFile = new ZipFile(new File("src/test/resources/zip/sample-zip-file.zip"));
```
- Создаётся объект `ZipFile`, который позволяет работать с ZIP-архивом, как с коллекцией файлов.
- Указываем путь к ZIP-файлу на диске — `src/test/resources/zip/sample-zip-file.zip`.
- С помощью `zipFile` мы потом будем доставать содержимое конкретных файлов из архива.
```java
ZipInputStream zipInputStream = new ZipInputStream(classLoader.getResourceAsStream("zip/sample-zip-file.zip"));
```
- Создаём поток для чтения архива в виде последовательности записей `ZipEntry`.
- Используется `classLoader.getResourceAsStream(...`), чтобы найти ZIP-файл в ресурсах (вместо указания абсолютного пути).
- `ZipInputStream` позволяет итерироваться по файлам внутри архива — получать их имена и метаданные.
```java
ZipEntry entry;
```
- `ZipEntry` — это класс, который описывает один файл или папку внутри ZIP-архива.  
Он хранит:
- имя файла `entry.getName()`,
- размер `entry.getSize()`,
- другие метаданные.
```java
while((entry = zipInputStream.getNextEntry()) != null) {
```
- `while (...) != null` — перебираем все файлы в архиве, пока они не закончатся.
- `getNextEntry()` — берёт следующий файл из архива.
```java
Assertions.assertEquals("sample.txt", entry.getName());
```
- Проверяем, что имя текущего файла в архиве равно "sample.txt".
```java
try (InputStream inputStream = zipFile.getInputStream(entry)) {
```
- Открываем поток чтения содержимого конкретного файла `entry` из архива через `zipFile`
- Используем `try-with-resources`, чтобы автоматически закрыть поток после выхода из блока

#### JSON
Гугловская библиотека для чтения json:
```groovy
// https://mvnrepository.com/artifact/com.google.code.gson/gson
implementation 'com.google.code.gson:gson:2.13.1'
```
Еще одна библиотека для работы с json:
```groovy
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
implementation 'com.fasterxml.jackson.core:jackson-core:2.19.0'
```
```java
    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(classLoader.getResourceAsStream("example.json"))) {
            JsonObject actual = gson.fromJson(reader, JsonObject.class);
            Assertions.assertEquals("example glossary", actual.get("title").getAsString());

            JsonObject inner = actual.get("glossary").getAsJsonObject();
            Assertions.assertEquals("SGML", inner.get("SortAs").getAsString());
        }
    }
```

Пояснение:
```java
try (Reader reader = new InputStreamReader(classLoader.getResourceAsStream("example.json"))) {
```
- `try-with-resources` — создаётся `Reader`, который будет автоматически закрыт после выполнения блока.
- `classLoader.getResourceAsStream("example.json")` — открывает файл `example.json` из ресурсов
- `InputStreamReader` преобразует поток байтов в поток символов, чтобы его можно было читать как текст.
```java
JsonObject actual = gson.fromJson(reader, JsonObject.class);
```
- `gson.fromJson(reader, JsonObject.class)` — парсит JSON-файл и превращает его в объект типа `JsonObject` (дерево JSON-данных).
- В переменной `actual` теперь лежит весь JSON, как структура данных, к которой можно обращаться по ключам.
```java
Assertions.assertEquals("example glossary", actual.get("title").getAsString());
```
- Достаём значение по ключу `title` из верхнего уровня JSON:
- `actual.get("title")` → элемент JSON,
- `.getAsString()` → его строковое значение.
- Сравниваем с ожидаемым значением "`example glossary`".
```java
JsonObject inner = actual.get("glossary").getAsJsonObject();
```
- Извлекаем вложенный в `actual` объект по ключу `glossary` и приводим его к `JsonObject`.
- Теперь в переменной `inner` лежит внутренний объект JSON, с которым можно работать дальше.
```java
Assertions.assertEquals("SGML", inner.get("SortAs").getAsString());
```
- Из внутреннего объекта достаём поле `SortAs` и проверяем, что оно равно `SGML`.  

**Модели для json.**  
`@SerializedName("ID") `- нужен для того, чтобы java прочла название с большой буквы
```json
{
  "ID": 234234,
  "glossary": {
    "SortAs": "SGML"
  }
}
```
```java
public class Glossary {
    @SerializedName("ID")
    private Integer id;
    private GlossaryInner glossary;

    public Integer getID() {
        return id;
    }
    public void setID(Integer id) {
        this.id = id;
    }

    public GlossaryInner getGlossary() {
        return glossary;
    }
    public void setGlossary(GlossaryInner glossary) {
        this.glossary = glossary;
    }
}

```
```java
public class GlossaryInner {
    @SerializedName("SortAs")
    private String sortAs;

    public String getSortAs() {
        return sortAs;
    }
    public void setSortAs(String sortAs) {
        this.sortAs = sortAs;
    }
}

```

### Домашнее задание для урока 9:
1. **Работа с zip**  
1.1 Запаковать в zip архив несколько разных файлов - pdf, xlsx, csv  
1.2 Положить его в ресурсы  
1.3 Реализовать чтение и проверку содержимого каждого файла из архива  

2. **Разбор JSON**  
2.1 Реализовать разбор json файла библиотекой Jackson
2.2 Придумать реальный объект и описать его в виде json (можешь Lombok использовать)
2.3 В идеале json должен содержать массив  