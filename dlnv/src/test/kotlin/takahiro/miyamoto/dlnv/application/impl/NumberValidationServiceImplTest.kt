package takahiro.miyamoto.dlnv.application.impl

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import takahiro.miyamoto.dlnv.domain.model.license.LicenseNumber
import takahiro.miyamoto.dlnv.domain.model.license.PublicSafetyCommission

/**
 * @author miyamoto_ta
 */
class NumberValidationServiceImplTest {

    val numberValidationService = NumberValidationServiceImpl()

    @Test fun validateValidRangeOfLicenseAcquisitionYear() {
        // 免許証取得年の下二桁なので範囲は0-99
        val minYear = 0
        val minYearLicense = getDefaultLicense(licenseAcquisitionYear = minYear)
        val minYearActual = numberValidationService.validate(minYearLicense)
        assertTrue(minYearActual)

        val maxYear = 99
        val maxYearLicense = getDefaultLicense(licenseAcquisitionYear = maxYear)
        val maxYearActual = numberValidationService.validate(maxYearLicense)
        assertTrue(maxYearActual)
    }

    @Test fun validateInvalidRangeOfLicenseAcquisitionYear() {
        // 免許証取得年の下二桁なので範囲は0-99
        val minYear = -1
        val minYearLicense = getDefaultLicense(licenseAcquisitionYear = minYear)
        val minYearActual = numberValidationService.validate(minYearLicense)
        assertFalse(minYearActual)

        val maxYear = 100
        val maxYearLicense = getDefaultLicense(licenseAcquisitionYear = maxYear)
        val maxYearActual = numberValidationService.validate(maxYearLicense)
        assertFalse(maxYearActual)
    }

    private fun getDefaultLicense(
            publicSafetyCommission: PublicSafetyCommission = PublicSafetyCommission(10, "test"),
            licenseAcquisitionYear: Int = 0,
            controlNumber: Int = 123456,
            checkDigit: Int = 6,
            reissuesNumber: Int = 0
    ) = LicenseNumber(publicSafetyCommission, licenseAcquisitionYear, controlNumber, checkDigit, reissuesNumber)

}