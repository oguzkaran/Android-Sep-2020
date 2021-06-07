package org.csystem.app;

import org.csystem.util.NumberUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FactorialTestCase {
    private final DataInfo m_dataInfo;

    private static class DataInfo {
        int val;
        int result;

        public DataInfo(int val, int result)
        {
            this.val = val;
            this.result = result;
        }
    }

    public FactorialTestCase(DataInfo dataInfo)
    {
        m_dataInfo = dataInfo;
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> collectTestData() throws IOException
    {
        return Files.newBufferedReader(Path.of("factorial-test-data.txt"))
                .lines()
                .map(str -> str.split("[ ,]+"))
                .map(di -> new DataInfo(Integer.parseInt(di[0]), Integer.parseInt(di[1])))
                .collect(Collectors.toList());
    }

    @Test
    public void test()
    {
        assertEquals(m_dataInfo.result, NumberUtil.factorial(m_dataInfo.val));
    }
}
