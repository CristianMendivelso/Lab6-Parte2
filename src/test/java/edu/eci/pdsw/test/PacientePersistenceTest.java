package edu.eci.pdsw.test;

/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.persistence.DaoFactory;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class PacientePersistenceTest {
    
    public PacientePersistenceTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void databaseConnectionTest2() throws IOException, PersistenceException{
        InputStream input = null;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties=new Properties();
        properties.load(input);
        
        DaoFactory daof=DaoFactory.getInstance(properties);
        
        daof.beginSession();
                
        //IMPLEMENTACION DE LAS PRUEBA
   
       
        
        Usuario p = new Usuario("cristian.soto-a@mail.escuelaing.edu.co","Christian Soto");
        daof.getDaoUsuario().save(p);
    
        try{
            daof.getDaoUsuario().save(p);
            assertTrue("Se registraron dos usuarios.",false);
        }
        catch(PersistenceException e){
            assertTrue("No se pueden registrar Usuarios repetidos.",true);
        }
        daof.commitTransaction();
        daof.endSession();        
    }

    @Test
    public void databaseConnectionTest1() throws IOException, PersistenceException{
        InputStream input = null;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties=new Properties();
        properties.load(input);
        
        DaoFactory daof=DaoFactory.getInstance(properties);
        
        daof.beginSession();
                
        //IMPLEMENTACION DE LAS PRUEBA
   

        Usuario u = new Usuario("correoprueba","nombre");
        daof.getDaoUsuario().save(u);
        Usuario nu= daof.getDaoUsuario().load("correoprueba");
        assertEquals("No se guardo correctamente el usuario",u.getNombre(),nu.getNombre());
        assertEquals("No se guardo correctamente el usuario",u.getEmail(),nu.getEmail());
        daof.commitTransaction();
        daof.endSession();        
    }    
}
