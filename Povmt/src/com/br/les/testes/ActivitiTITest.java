package com.br.les.testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.br.les.timeitup.ActivityTI;

public class ActivitiTITest {
    ActivityTI a1, a2, a3;

    @Before
    public void setup(){
        a1 = new ActivityTI("estudar", 2, 0, 2);
        a2 = new ActivityTI("estudar", 3, 30, 2);
        a3 = new ActivityTI("trabalhar", 4, 0, 2);        
    }
    
    @Test
    public void testEquals() {
        Assert.assertTrue(a1.equals(a2));
        Assert.assertFalse(a1.equals(a3));
    }

    @Test
    public void getNameTest() {
        Assert.assertEquals("estudar", a1.getName());

        a1.setName("estudar les");
        Assert.assertEquals("estudar les", a1.getName());
    }

    @Test
    public void getHourTest() {
        Assert.assertEquals(2, a1.getHour());

        a1.setHour(3);
        Assert.assertEquals(3, a1.getHour());

    }

    @Test
    public void getMinuteTest() {
        Assert.assertEquals(0, a1.getMinute());

        a1.setMinute(35);
        Assert.assertEquals(35, a1.getMinute());

    }

    @Test
    public void getPriorityTest() {
        Assert.assertEquals("High", a1.getPriority());

        a1.setPriority(1);
        Assert.assertEquals("Medium", a1.getPriority());

    }

    @Test
    public void addTimeTest() {
        Assert.assertEquals(2, a1.getHour());

        a1.addTime(2, 0);
        Assert.assertEquals(4, a1.getHour());

    }

}
