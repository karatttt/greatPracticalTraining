package com.softcostEstimator.evaluate.transform;

import cn.hutool.core.lang.UUID;
import com.spire.doc.Document;
import com.spire.doc.FieldType;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.Paragraph;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.renderer.text.TextContentRenderer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TransToHtml implements TransUtil{
    @Override
    public String transForm(String text) {
        //获取题目标题
        String[] strings = text.split("\n");
        String[] subStrings = strings[0].split(" ");
        String title = subStrings[1];
        Parser parser = Parser.builder().build();
        Node document = parser.parse(text);
         HtmlRenderer renderer = HtmlRenderer.builder().build(); //可以markdown转html
        String txt = renderer.render(document);
        Document doc = new Document();
        Section section = doc.addSection();
        Paragraph paragraph = section.addParagraph();
        paragraph.appendField("A1", FieldType.Field_Doc_Variable);
        doc.getVariables().add("A1", txt);
        doc.isUpdateFields(true);
        String outputPath = "file/"+title+ UUID.randomUUID()+ ".html";
        doc.saveToFile(outputPath, FileFormat.Html);
        doc.dispose();
        File file = new File(outputPath);
        return file.getAbsolutePath();
    }
}
