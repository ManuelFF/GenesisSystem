/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ZTestingPackage;

import org.quartz.SchedulerException;

/**
 *
 * @author USUARIO
 */
public class testincallScheduler 
{
    mainSchedulerController s = new mainSchedulerController();

    public static void main(String[] arg) throws SchedulerException
    {
        mainSchedulerController.Scheduler(MyObject.class,1);
    }
    
}