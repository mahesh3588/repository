/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;

import javax.swing.*;
import java.awt.print.*;

public class PrintUtil implements Printable {

	private Component componentToBePrinted;

	public PrintUtil(Component componentToBePrinted) {
	   this.componentToBePrinted = componentToBePrinted;
	}

	
	public static void printComponent(Component c) {
	    new PrintUtil(c).print();
	}
	
	@Override
	public int print(Graphics g, PageFormat pageFormat, int pageIndex)throws PrinterException 
	{
		 if (pageIndex > 0) 
		 {
                    return(NO_SUCH_PAGE);
                 }
		 else
		 {
                    Graphics2D g2d = (Graphics2D)g;
                    
                    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                    //g2d.translate(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());        
                    disableDoubleBuffering(componentToBePrinted);
                    componentToBePrinted.paint(g2d);
                    enableDoubleBuffering(componentToBePrinted);
                    return(PAGE_EXISTS);
                 }
	}
	
	public static void disableDoubleBuffering(Component c)
	{
	   RepaintManager currentManager = RepaintManager.currentManager(c);
	   currentManager.setDoubleBufferingEnabled(false);
	}

	public static void enableDoubleBuffering(Component c)
	{
	  RepaintManager currentManager = RepaintManager.currentManager(c);
	  currentManager.setDoubleBufferingEnabled(true);
	}

    public void print() 
    {
	    PrinterJob printJob = PrinterJob.getPrinterJob();
	    printJob.setPrintable(this);
	    if (printJob.printDialog())
	    try 
	    {
	      printJob.print();
	    } catch(PrinterException pe)
	    {
	      System.out.println("Error printing: " + pe);
	    }
    }


}


