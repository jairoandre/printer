package br.com.vah.printer.schedulers;

import br.com.vah.printer.daos.TestDao;
import br.com.vah.printer.reports.ReportLoader;
import br.com.vah.printer.utils.PrinterUtils;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.print.PrintService;

/**
 * Created by Jairoportela on 20/12/2016.
 */

@Stateless
public class MainScheduler {

  private
  @Inject
  ReportLoader reportLoader;

  private
  @Inject
  TestDao testDao;

  @Schedule(second = "0", minute = "*", hour = "*")
  public void other() {
    try {
      System.out.println("Printing...");
      PrintService print = PrinterUtils.loadPrintService("Microsoft XPS Document Writer");
      reportLoader.printReport("nutricao", testDao.list(), print);
      Thread.sleep(3000l);
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }

  }
}
