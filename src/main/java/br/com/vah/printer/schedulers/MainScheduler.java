package br.com.vah.printer.schedulers;

import br.com.vah.printer.daos.TestDao;
import br.com.vah.printer.reports.ReportLoader;
import br.com.vah.printer.utils.PrinterUtils;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

/**
 * Created by Jairoportela on 20/12/2016.
 */

@Stateless
@Startup
public class MainScheduler {

  private @Inject
  ReportLoader reportLoader;

  private @Inject
  TestDao testDao;

  @Schedule(second = "*/10", minute="*", hour="*")
  public void other() {
    try {
      System.out.println("Printing...");
      //PrintService print = PrinterUtils.loadPrintService("T.I - Brother_HL-6180DW");
      reportLoader.printReport("nutricao", testDao.list(), null);
      Thread.sleep(3000l);
    } catch (InterruptedException ie) {

    }

  }
}
