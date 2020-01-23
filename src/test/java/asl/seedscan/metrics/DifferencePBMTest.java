package asl.seedscan.metrics;

import static org.junit.Assert.assertEquals;

import asl.testutils.MetricTestMap;
import asl.testutils.ResourceManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DifferencePBMTest {

  private DifferencePBM metric;
  private static MetricData data;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    data = ResourceManager.loadANMOMainTestCase();
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    data = null;
  }

  @Test
  public void testProcess4_8() throws Exception {
    /* The String key matches the MetricResult ids */

    //TEST 4 - 8
    metric = new DifferencePBM();
    metric.add("lower-limit", "4");
    metric.add("upper-limit", "8");
    metric.setData(data);
    MetricTestMap expect = new MetricTestMap();
    expect.put("00-10,LHZ-LHZ",    0.0277, 1E-4);
    expect.put("00-10,LHND-LHND",  0.1678, 1E-4);
    expect.put("00-10,LHED-LHED", -0.0590, 1E-4);
    TestUtils.testMetric(metric, expect);
  }

  @Test
  public void testProcess18_22() throws Exception {
    //TEST 18 - 22
    metric = new DifferencePBM();
    metric.add("lower-limit", "18");
    metric.add("upper-limit", "22");
    metric.setData(data);
    MetricTestMap expect = new MetricTestMap();
    expect.put("00-10,LHZ-LHZ",   -0.0122053, 1E-5);
    expect.put("00-10,LHND-LHND",  0.2018929, 1E-5);
    expect.put("00-10,LHED-LHED", -0.0444764, 1E-5);
    TestUtils.testMetric(metric, expect);
  }

  @Test
  public void testProcess90_110() throws Exception {
    //TEST 90 - 110
    metric = new DifferencePBM();
    metric.add("lower-limit", "90");
    metric.add("upper-limit", "110");
    metric.setData(data);
    MetricTestMap expect = new MetricTestMap();
    expect.put("00-10,LHZ-LHZ",   -0.873422, 1E-5);
    expect.put("00-10,LHND-LHND", -1.468633, 1E-5);
    expect.put("00-10,LHED-LHED",  1.898321, 1E-5);
    TestUtils.testMetric(metric, expect);
  }

  @Test
  public void testProcess200_500() throws Exception {
    //TEST 200 - 500
    metric = new DifferencePBM();
    metric.add("lower-limit", "200");
    metric.add("upper-limit", "500");
    metric.setData(data);
    MetricTestMap expect = new MetricTestMap();
    expect.put("00-10,LHZ-LHZ",    2.378944, 1E-5);
    expect.put("00-10,LHND-LHND", -3.201420, 1E-5);
    expect.put("00-10,LHED-LHED",  6.398307, 1E-5);
    TestUtils.testMetric(metric, expect);
  }

  @Test
  public void testProcess200_500_Reverse() throws Exception {
    //TEST Change in base
    //Results should be negative of 00-10
    metric = new DifferencePBM();
    metric.add("lower-limit", "200");
    metric.add("upper-limit", "500");
    metric.add("base-channel", "10-LH");
    metric.setData(data);
    MetricTestMap expect = new MetricTestMap();
    expect.put("10-00,LHZ-LHZ" ,  -2.378944, 1E-5);
    expect.put("10-00,LHND-LHND",  3.201420, 1E-5);
    expect.put("10-00,LHED-LHED", -6.398307, 1E-5);
    TestUtils.testMetric(metric, expect);

  }

  @Test
  public final void testGetVersion() throws Exception {
    metric = new DifferencePBM();
    assertEquals(2, metric.getVersion());
  }

  @Test
  public final void testGetBaseName() throws Exception {
    metric = new DifferencePBM();
    assertEquals("DifferencePBM", metric.getBaseName());
  }

}
