/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DvdLibraryController;
import com.sg.dvdlibrary.dao.DvdLibraryAuditDao;
import com.sg.dvdlibrary.dao.DvdLibraryAuditDaoFileImpl;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.sg.dvdlibrary.service.DvdLibraryServiceLayer;
import com.sg.dvdlibrary.service.DvdLibraryServiceLayerImpl;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wafa Mekki
 */
public class App {
    public static void main(String[] args) {
        //Implement DI using XML configuration
        ApplicationContext appContext
                = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        DvdLibraryController controller = appContext.getBean("controller", DvdLibraryController.class);
        controller.run();

        //Without Spring framework
        /*UserIO myIo = new UserIOConsoleImpl();
        DvdLibraryView myView = new DvdLibraryView(myIo);
        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl();
        DvdLibraryAuditDao myAuditDao = new DvdLibraryAuditDaoFileImpl();
        DvdLibraryServiceLayer myService = new DvdLibraryServiceLayerImpl(myDao, myAuditDao);
        DvdLibraryController controller = new DvdLibraryController(myView, myService);
        controller.run();*/
    }
}

