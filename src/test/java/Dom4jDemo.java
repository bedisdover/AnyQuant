import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 宋益明 on 16-3-19.
 * <p>
 * 测试dom4j的使用
 */
public class Dom4jDemo {

    /**
     * Creates a new instance of Dom4jDemo
     */
    public Dom4jDemo() {
    }

    /**
     * The method createXML
     * 建立一个XML文档,文档名由输入属性决定
     *
     * @param fileName 需建立的文件名
     * @return 返回操作结果
     */
    public boolean createXML(String fileName) {
        boolean isOk = false;
        //建立document对象
        Document document = DocumentHelper.createDocument();
        //建立XML文档的根books
        Element books = document.addElement("books");
        books.addComment("This is a test for dom4j");//加入一行注释
        Element book = books.addElement("book");//加入第一个book节点
        book.addAttribute("show", "yes");//加入show属性内容
        Element title = book.addElement("title");//加入title节点
        title.setText("Dom4j Tutorials");//为title设置内容
        /**加入第二个book节点*/
        book = books.addElement("book");
        book.addAttribute("show", "yes");
        title = book.addElement("title");
        title.setText("Lucene Studying");
        /**加入第三个book节点*/
        book = books.addElement("book");
        book.addAttribute("show", "no");
        title = book.addElement("title");
        title.setText("Lucene in Action");
        /**加入owner节点*/
        Element owner = books.addElement("owner");
        owner.setText("ultraviolet");
        /**
         *将doc中的内容写入xml文件中
         **/
        try {
            FileWriter file = new FileWriter(new File(fileName));
            XMLWriter writer = new XMLWriter(file);
            writer.write(document);
            writer.close();
            isOk = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isOk;
    }

    /**
     * the method modifyXML
     * 如果book节点中show属性的内容为yes,则修改成no
     * 把owner项内容改为Tshinghua，并添加date节点
     * 若title内容为Dom4j Tutorials,则删除该节点
     */
    public boolean modifyXML(String oldFileName, String newFileName) {
        boolean isOk = false;
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File(oldFileName));
            /**修改内容之一: 如果book节点中show属性的内容为yes,则修改成no*/
            List list = doc.selectNodes("/books/book/@show");
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Attribute attr = (Attribute) iterator.next();
                if (attr.getValue().equals("yes"))
                    attr.setValue("no");
            }
            /**
             *修改内容之二: 把owner项内容改为Tshinghua
             *并在owner节点中加入date节点,
             * date节点的内容为2004-09-11,
             * 还为date节点添加一个属性type
             */
            list = doc.selectNodes("/books/owner");
            iterator = list.iterator();
            if (iterator.hasNext()) {
                Element owner = (Element) iterator.next();
                owner.setText("Tshinghua");
                Element date = owner.addElement("date");
                date.setText("2006-07-30");
                date.addAttribute("type", "Gregorian calendar");
            }
            /**修改内容之三: 若title内容为Dom4j Tutorials,则删除该节点*/
            list = doc.selectNodes("/books/book");
            iterator = list.iterator();
            while (iterator.hasNext()) {
                Element elem = (Element) iterator.next();
                Iterator iterElem = elem.elementIterator("title");
                if (iterElem.hasNext()) {
                    Element remTitle = (Element) iterElem.next();
                    if (remTitle.getText().trim().equals("Dom4j Tutorials"))
                        elem.remove(remTitle);
                }
            }
            //将doc中的内容写入文件中
            try {
                FileWriter newFile = new FileWriter(new File(newFileName));
                XMLWriter newWriter = new XMLWriter(newFile);
                newWriter.write(doc);
                newWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            isOk = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isOk;
    }

    /**
     * the method formatXML
     * -------------------------
     * 默认的输出方式为紧凑方式，默认编码为UTF-8，
     * 但对于我们的应用而言，一般都要用到中文，
     * 并且希望显示时按自动缩进的方式的显示，这就需用到OutputFormat类。
     */
    public boolean formatXML(String fileName) {
        boolean isOk = false;
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File(fileName));
            XMLWriter formatWriter = null;
            /**
             *格式化输出,类型IE浏览一样
             *指定XML编码
             */
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("gb2312");
            formatWriter = new XMLWriter(new FileWriter(new File(fileName)), format);
            formatWriter.write(doc);
            formatWriter.close();

            isOk = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isOk;
    }

    /**
     * the method readXML
     */
    public void readXML(String fileName) {
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File(fileName));
            List list = doc.selectNodes("/books/book");
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Element book = (Element) iter.next();
                Iterator title = book.elementIterator("title");
                if (title.hasNext()) {
                    Element elemTitle = (Element) title.next();
                    System.out.print("Title: " + elemTitle.getText());
                    System.out.print(" Show: " + book.attributeValue("show") + "\n");
                }
            }

            list = doc.selectNodes("/books/owner");
            iter = list.iterator();
            if (iter.hasNext()) {
                Element owner = (Element) iter.next();
                System.out.println("Owner: " + owner.getText());
                System.out.println("Date: " + owner.elementText("date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * the method main
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();//开始计时
        Dom4jDemo dom4jDemo = new Dom4jDemo();
        String fileName = "dom4jDemo.xml";
        String newFileName = "new_dom4jDemo.xml";
        System.out.println(fileName + "正在创建中...");
        boolean isOk_create = dom4jDemo.createXML(fileName);
        if (isOk_create) System.out.println(fileName + "创建成功!");
        else System.out.println("创建失败!请检查后重新再试!");

        System.out.println("正在更新中...");
        boolean isOk_modi = dom4jDemo.modifyXML(fileName, newFileName);
        if (isOk_modi) System.out.println("恭喜,更新完成!");
        else System.out.println("更新失败!请检查后重新再试!");

        System.out.println("正在格式化中...");
        boolean isOk_format = dom4jDemo.formatXML(fileName);
        if (isOk_format) System.out.println("恭喜,格式化完成!");
        else System.out.println("格式化失败!请检查后重新再试!");

        System.out.println("正在读取文件 " + fileName + " ...");
        System.out.println("------------------------------------------------");
        dom4jDemo.readXML(fileName);
        System.out.println("------------------------------------------------");
        System.out.println(fileName + "读取成功!");
        System.out.println("正在读取文件 " + newFileName + " ...");
        System.out.println("------------------------------------------------");
        dom4jDemo.readXML(newFileName);
        System.out.println("------------------------------------------------");
        System.out.println(fileName + "读取成功!");
        System.out.println("一共耗时： " + (System.currentTimeMillis() - start) + "毫秒!");
    }
}