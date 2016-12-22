package br.com.vah.printer.utils;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

/**
 * Created by Jairoportela on 20/12/2016.
 */
public class PrinterUtils {

  public static PrintService loadPrintService(String print) {
    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
    for (PrintService printService : printServices) {
      if (printService.getName().equals(print)) {
        return printService;
      }
    }
    for (PrintService printService : printServices) {
      if (printService.getName().contains(print)) {
        return printService;
      }
    }
    return null;
  }

  public static void main(String[] args) {
    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
    for (PrintService print : printServices) {
      System.out.println(print.getName());
    }
  }

}
