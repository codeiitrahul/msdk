/* 
 * (C) Copyright 2015-2016 by MSDK Development Team
 *
 * This software is dual-licensed under either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */

package io.github.msdk.io.nativeformats;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import io.github.msdk.datamodel.datastore.DataPointStore;
import io.github.msdk.datamodel.datastore.DataPointStoreFactory;
import io.github.msdk.datamodel.msspectra.MsSpectrumType;
import io.github.msdk.datamodel.rawdata.MsScan;
import io.github.msdk.datamodel.rawdata.PolarityType;
import io.github.msdk.datamodel.rawdata.RawDataFile;
import io.github.msdk.util.MsSpectrumUtil;

public class ThermoRawImportMethodTest {

    private static final String TEST_DATA_PATH = "src/test/resources/";

    @SuppressWarnings("null")
    @Test
    public void testRP240K01() throws Exception {

        // Run this test only on Windows
        Assume.assumeTrue(System.getProperty("os.name").startsWith("Windows"));

        // Create the data structures
        DataPointStore dataStore = DataPointStoreFactory.getMemoryDataStore();
        double mzBuffer[] = new double[10000];
        float intensityBuffer[] = new float[10000];

        // Import the file
        File inputFile = new File(TEST_DATA_PATH + "RP240K_01.raw");
        Assert.assertTrue(inputFile.canRead());
        ThermoRawImportMethod importer = new ThermoRawImportMethod(inputFile,
                dataStore);
        RawDataFile rawFile = importer.execute();
        Assert.assertNotNull(rawFile);
        Assert.assertEquals(1.0, importer.getFinishedPercentage(), 0.0001);

        // The file has 55 scans
        List<MsScan> scans = rawFile.getScans();
        Assert.assertNotNull(scans);
        Assert.assertEquals(55, scans.size());

        // 10th scan, #10
        MsScan scan10 = scans.get(9);
        Assert.assertEquals(new Integer(10), scan10.getScanNumber());
        Assert.assertEquals(MsSpectrumType.PROFILE, scan10.getSpectrumType());
        Assert.assertEquals(new Integer(1),
                scan10.getMsFunction().getMsLevel());
        Assert.assertEquals(37.692f,
                scan10.getChromatographyInfo().getRetentionTime(), 0.01f);
        Assert.assertEquals(PolarityType.POSITIVE, scan10.getPolarity());
        mzBuffer = scan10.getMzValues(mzBuffer);
        intensityBuffer = scan10.getIntensityValues(intensityBuffer);
        Assert.assertEquals(9400, (int) scan10.getNumberOfDataPoints());
        Float scan10maxInt = MsSpectrumUtil.getMaxIntensity(intensityBuffer,
                scan10.getNumberOfDataPoints());
        Assert.assertEquals(7.44E6f, scan10maxInt, 1E5f);
        Double scan10basePeakMz = mzBuffer[MsSpectrumUtil.getBasePeakIndex(
                intensityBuffer, scan10.getNumberOfDataPoints())];
        Assert.assertEquals(854.0946901, scan10basePeakMz, 0.000001);

        // 50th scan, #50
        MsScan scan50 = scans.get(49);
        Assert.assertEquals(new Integer(50), scan50.getScanNumber());
        Assert.assertEquals(MsSpectrumType.PROFILE, scan50.getSpectrumType());
        Assert.assertEquals(new Integer(1),
                scan50.getMsFunction().getMsLevel());
        Assert.assertEquals(202.332f,
                scan50.getChromatographyInfo().getRetentionTime(), 0.01f);
        Assert.assertEquals(PolarityType.POSITIVE, scan50.getPolarity());
        mzBuffer = scan50.getMzValues(mzBuffer);
        intensityBuffer = scan50.getIntensityValues(intensityBuffer);
        Assert.assertEquals(7357, (int) scan50.getNumberOfDataPoints());
        Float scan50maxInt = MsSpectrumUtil.getMaxIntensity(intensityBuffer,
                scan50.getNumberOfDataPoints());
        Assert.assertEquals(1.33E7f, scan50maxInt, 1E5f);
        Double scan50basePeakMz = mzBuffer[MsSpectrumUtil.getBasePeakIndex(
                intensityBuffer, scan50.getNumberOfDataPoints())];
        Assert.assertEquals(854.0933557, scan50basePeakMz, 0.000001);

    }

    @SuppressWarnings("null")
    @Test
    public void testDrugX01() throws Exception {

        // Run this test only on Windows
        Assume.assumeTrue(System.getProperty("os.name").startsWith("Windows"));

        // Create the data structures
        DataPointStore dataStore = DataPointStoreFactory.getMemoryDataStore();
        double mzBuffer[] = new double[10000];
        float intensityBuffer[] = new float[10000];

        // Import the file
        File inputFile = new File(TEST_DATA_PATH + "drugx_01.raw");
        Assert.assertTrue(inputFile.canRead());
        ThermoRawImportMethod importer = new ThermoRawImportMethod(inputFile,
                dataStore);
        RawDataFile rawFile = importer.execute();
        Assert.assertNotNull(rawFile);
        Assert.assertEquals(1.0, importer.getFinishedPercentage(), 0.0001);

        // The file has 315 scans
        List<MsScan> scans = rawFile.getScans();
        Assert.assertNotNull(scans);
        Assert.assertEquals(315, scans.size());

        // 220th scan, #220
        MsScan scan220 = scans.get(219);
        Assert.assertEquals(new Integer(220), scan220.getScanNumber());
        Assert.assertEquals(MsSpectrumType.CENTROIDED,
                scan220.getSpectrumType());
        Assert.assertEquals(new Integer(2),
                scan220.getMsFunction().getMsLevel());
        Assert.assertEquals("srm", scan220.getMsFunction().getName());
        Assert.assertEquals(293.97f,
                scan220.getChromatographyInfo().getRetentionTime(), 0.01f);
        Assert.assertEquals(1, scan220.getIsolations().size());
        Assert.assertEquals(469.40,
                scan220.getIsolations().get(0).getPrecursorMz(), 0.01);
        Assert.assertEquals(PolarityType.POSITIVE, scan220.getPolarity());
        mzBuffer = scan220.getMzValues(mzBuffer);
        intensityBuffer = scan220.getIntensityValues(intensityBuffer);
        Assert.assertEquals(3, (int) scan220.getNumberOfDataPoints());
        Float scan220maxInt = MsSpectrumUtil.getMaxIntensity(intensityBuffer,
                scan220.getNumberOfDataPoints());
        Assert.assertEquals(2.03E5f, scan220maxInt, 1E3f);
        Double scan220basePeakMz = mzBuffer[MsSpectrumUtil.getBasePeakIndex(
                intensityBuffer, scan220.getNumberOfDataPoints())];
        Assert.assertEquals(424.4257202, scan220basePeakMz, 0.000001);

    }

}