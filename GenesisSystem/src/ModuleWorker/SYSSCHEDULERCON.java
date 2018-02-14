/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author USUARIO
 */
public class SYSSCHEDULERCON 
{

    /*
        Esta clase se encarga de controlar el controlador basico de las tareas programadas (pragmaticas)
        las cuales seran usadas en genesis
    */
    
    public static void Scheduler(Class myobjt) throws SchedulerException
    {
       
        JobDetail j = JobBuilder.newJob(myobjt).build();
        
        Trigger t = TriggerBuilder.newTrigger().withIdentity("CroneTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(010)
                    .repeatForever()).build();
        
        Scheduler s = StdSchedulerFactory.getDefaultScheduler();

        s.start();
        s.scheduleJob(j,t);
    }
    
}
