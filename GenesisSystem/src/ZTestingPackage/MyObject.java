/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ZTestingPackage;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author USUARIO
 */
public class MyObject implements Job
{

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException 
    {
        System.out.println("My Schelduler is now running");
        
        
    }
    
}
