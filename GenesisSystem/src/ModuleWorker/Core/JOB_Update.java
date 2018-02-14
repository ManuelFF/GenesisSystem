/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.Core;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author USUARIO
 */
public class JOB_Update implements Job
{
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException
    {
        //AQUI IRA LA LOGICA DE PROGRAMACION PARA DETERMINAR SI HAY UNA     
        //ACTUALIZACION DISPONIBLE 
        
        System.out.println("HELLO WORLD");
        
        
    }
}
