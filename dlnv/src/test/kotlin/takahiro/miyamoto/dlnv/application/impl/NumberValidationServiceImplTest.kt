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

    @Test
    fun validateValidRangeOfLicenseAcquisitionYear() {
        // 免許証取得年の下二桁なので範囲は0-99
        val minYear = 0
        val minYearLicense = getDefaultLicense(licenseAcquisitionYear = minYear, checkDigit = getModulus11(1000123456))
        val minYearActual = numberValidationService.validate(minYearLicense)
        assertTrue(minYearActual)

        val maxYear = 99
        val maxYearLicense = getDefaultLicense(licenseAcquisitionYear = maxYear, checkDigit = getModulus11(1099123456))
        val maxYearActual = numberValidationService.validate(maxYearLicense)
        assertTrue(maxYearActual)
    }

    @Test
    fun validateInvalidRangeOfLicenseAcquisitionYear() {
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

    @Test
    fun validateValidRangeOfControlNumber() {
        // 管理用番号 6桁
        val min = 0
        val minLicense = getDefaultLicense(controlNumber = min, checkDigit = getModulus11(1000000000))
        val minActual = numberValidationService.validate(minLicense)
        assertTrue(minActual)

        val max = 999999
        val maxLicense = getDefaultLicense(controlNumber = max, checkDigit = getModulus11(1000999999))
        val maxActual = numberValidationService.validate(maxLicense)
        assertTrue(maxActual)
    }

    @Test
    fun validateInvalidRangeOfControlNumber() {
        // 管理用番号 6桁
        val min = -1
        val minLicense = getDefaultLicense(controlNumber = min)
        val minActual = numberValidationService.validate(minLicense)
        assertFalse(minActual)

        val max = 1000000
        val maxLicense = getDefaultLicense(controlNumber = max)
        val maxActual = numberValidationService.validate(maxLicense)
        assertFalse(maxActual)
    }

    @Test
    fun validateValidCheckDigit() {
        val license = getDefaultLicense(
                licenseAcquisitionYear = 98,
                controlNumber = 765432,
                checkDigit = getModulus11(1098765432)
        )

        val actual = numberValidationService.validate(license)
        assertTrue(actual)
    }

    @Test
    fun invalidateValidCheckDigit() {
        val license = getDefaultLicense(
                publicSafetyCommission = PublicSafetyCommission(40, "TEST"),
                licenseAcquisitionYear = 4,
                controlNumber = 10324,
                checkDigit = 7
        )

        val actual = numberValidationService.validate(license)
        assertFalse(actual)
    }

    private fun getModulus11(org: Long): Int {
        val reversed = org.toString().reversed().toList().map {
            it.toString().toInt()
        }.toList()

        val sum = reversed.withIndex().sumBy { it.value * (it.index % 6 + 2) }
        val remainder = sum % 11

        return if (remainder == 0 || remainder == 1) {
            0
        } else {
            11 - remainder
        }
    }


    private fun getDefaultLicense(
            publicSafetyCommission: PublicSafetyCommission = PublicSafetyCommission(10, "test"),
            licenseAcquisitionYear: Int = 0,
            controlNumber: Int = 123456,
            checkDigit: Int = 6,
            reissuesNumber: Int = 0
    ) = LicenseNumber(publicSafetyCommission, licenseAcquisitionYear, controlNumber, checkDigit, reissuesNumber)

}