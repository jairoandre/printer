package br.com.vah.printer.reports;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jairoportela on 21/12/2016.
 */
@Stateless
public class ReportLoader {

  private @Inject ServletContext context;

  public void printReport(String reportName, List list, PrintService printService) {
    try {
      if (list == null) {
        list = new ArrayList<>();
      }
      System.out.println("Imprimindo " + list.size() + " OS");
      Map<String, Object> params = new HashMap<>();
      InputStream imageIS = context.getResourceAsStream("/resources/imgs/logo.png");
      context.getResourceAsStream("/resources/imgs/logo.png");
      BufferedImage logo = ImageIO.read(imageIS);
      params.put("LOGO", logo);
      JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);
      InputStream reportIS = context.getResourceAsStream(String.format("/resources/reports/%s.jasper", reportName));
      JasperPrint jasperPrint = JasperFillManager.fillReport(reportIS, params, ds);
      ByteArrayInputStream in = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jasperPrint));
      if (printService == null) {
        System.out.println("Sem impressão");
      } else {
        Doc pdfDoc = new SimpleDoc(in, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        DocPrintJob printJob = printService.createPrintJob();
        printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
        System.out.println("Impressão realizada com sucesso!");
      }
      in.close();
    } catch (Exception e) {
      System.out.println("Erro inesperado na impressão!");
      e.printStackTrace();
    }
  }

}
