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

import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
public class IsPrimeNegativeTestCase {
    private final DataInfo m_dataInfo;

    private static class DataInfo {
        int val;
        boolean result;

        DataInfo(int val, boolean result)
        {
            this.val = val;
            this.result = result;
        }
    }

    public IsPrimeNegativeTestCase(DataInfo dataInfo)
    {
        m_dataInfo = dataInfo;
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> collectTestData() throws IOException
    {
        return Files.newBufferedReader(Path.of("primes-test-data.txt"))
                .lines()
                .map(str -> new DataInfo(Integer.parseInt(str), true))
                .collect(Collectors.toList());
    }

    @Test
    public void test()
    {
        var value = m_dataInfo.val;

        assertFalse(String.format("%d must be prime!!!", value), NumberUtil.isPrime(value));
    }
}
