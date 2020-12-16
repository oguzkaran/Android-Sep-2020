package org.csystem.app.samples.randomgenerator;

import org.csystem.util.ArrayUtil;
import org.csystem.util.StringUtil;
import org.csystem.util.datetime.Date;
import org.csystem.util.datetime.Time;

import java.util.Random;

public class RandomObjectArrayGenerator {
    private final Random m_random;
    private final Object [] m_objects;

    private Object getRandomObject()
    {
        int val = m_random.nextInt(7);

        Object object;

        switch (val) {
            case 0:
                object = StringUtil.getRandomTextTR(m_random.nextInt(15) + 1);
                break;
            case 1:
                object = m_random.nextInt(255) - 128;
                break;
            case 2:
                object = Date.randomDate(m_random);
                break;
            case 3:
                object = Time.randomTime(m_random);
                break;
            case 4:
                object = m_random.nextDouble();
                break;
            case 5:
                object = m_random.nextBoolean();
                break;
            default:
                object = ArrayUtil.getRandomArray(m_random, 10, 0, 256);
        }

        return object;
    }

    public RandomObjectArrayGenerator(int n)
    {
        m_random = new Random();
        m_objects = new Object[n];
    }

    public Object [] getObjects()
    {
        return m_objects;
    }

    public void run()
    {
        for (int i = 0; i < m_objects.length; ++i)
            m_objects[i] = getRandomObject();
    }
}
