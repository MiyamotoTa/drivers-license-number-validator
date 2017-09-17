package takahiro.miyamoto.dlnv.domain.model.license

import org.junit.Assert.*
import org.junit.Test

/**
 * @author miyamoto_ta
 */
class LicenseNumberTest {
    @Test
    fun validateNonNullPublicSafetyCommision() {
        val license = getDefaultLicense()

        val actual = license.validatePublicSafetyCommission()
        assertTrue(actual)
    }

    @Test
    fun validateNullPublicSafetyCommision() {
        val license = getDefaultLicense(
                publicSafetyCommission = null
        )

        val actual = license.validatePublicSafetyCommission()
        assertFalse(actual)
    }

    @Test
    fun validateValidRangeOfLicenseAcquisitionYear() {
        // 免許証取得年の下二桁なので範囲は0-99
        val minYear = 0
        val minYearLicense = getDefaultLicense(licenseAcquisitionYear = minYear, checkDigit = getModulus11(1000123456))
        val minYearActual = minYearLicense.validateLicenseAcquisitionYear()
        assertTrue(minYearActual)

        val maxYear = 99
        val maxYearLicense = getDefaultLicense(licenseAcquisitionYear = maxYear, checkDigit = getModulus11(1099123456))
        val maxYearActual = maxYearLicense.validateLicenseAcquisitionYear()
        assertTrue(maxYearActual)
    }

    @Test
    fun validateInvalidRangeOfLicenseAcquisitionYear() {
        // 免許証取得年の下二桁なので範囲は0-99
        val minYear = -1
        val minYearLicense = getDefaultLicense(licenseAcquisitionYear = minYear)
        val minYearActual = minYearLicense.validateLicenseAcquisitionYear()
        assertFalse(minYearActual)

        val maxYear = 100
        val maxYearLicense = getDefaultLicense(licenseAcquisitionYear = maxYear)
        val maxYearActual = maxYearLicense.validateLicenseAcquisitionYear()
        assertFalse(maxYearActual)
    }

    @Test
    fun validateValidRangeOfControlNumber() {
        // 管理用番号 6桁
        val min = 0
        val minLicense = getDefaultLicense(controlNumber = min, checkDigit = getModulus11(1000000000))
        val minActual = minLicense.validateControlNumber()
        assertTrue(minActual)

        val max = 999999
        val maxLicense = getDefaultLicense(controlNumber = max, checkDigit = getModulus11(1000999999))
        val maxActual = maxLicense.validateControlNumber()
        assertTrue(maxActual)
    }

    @Test
    fun validateInvalidRangeOfControlNumber() {
        // 管理用番号 6桁
        val min = -1
        val minLicense = getDefaultLicense(controlNumber = min)
        val minActual = minLicense.validateControlNumber()
        assertFalse(minActual)

        val max = 1000000
        val maxLicense = getDefaultLicense(controlNumber = max)
        val maxActual = maxLicense.validateControlNumber()
        assertFalse(maxActual)
    }

    @Test
    fun validateValidCheckDigit() {
        val license = getDefaultLicense(
                licenseAcquisitionYear = 98,
                controlNumber = 765432,
                checkDigit = getModulus11(1098765432)
        )

        val actual = license.validateCheckDigit()
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

        val actual = license.validateCheckDigit()
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

    @Test
    fun validateValidRangeOfReissuesNumber() {
        // 再発行回数1桁
        val min = 0
        val minLicense = getDefaultLicense(reissuesNumber = min)
        val minActual = minLicense.validateReissuesNumber()
        assertTrue(minActual)

        val max = 9
        val maxLicense = getDefaultLicense(reissuesNumber = max)
        val maxActual = maxLicense.validateReissuesNumber()
        assertTrue(maxActual)
    }

    @Test
    fun validateInvalidRangeOfReissuesNumber() {
        // 再発行回数1桁
        val min = -1
        val minLicense = getDefaultLicense(reissuesNumber = min)
        val minActual = minLicense.validateReissuesNumber()
        assertFalse(minActual)

        val max = 10
        val maxLicense = getDefaultLicense(reissuesNumber = max)
        val maxActual = maxLicense.validateReissuesNumber()
        assertFalse(maxActual)
    }

    private fun getDefaultLicense(
            publicSafetyCommission: PublicSafetyCommission? = PublicSafetyCommission(10, "test"),
            licenseAcquisitionYear: Int = 0,
            controlNumber: Int = 123456,
            checkDigit: Int = 6,
            reissuesNumber: Int = 0
    ) = LicenseNumber(publicSafetyCommission, licenseAcquisitionYear, controlNumber, checkDigit, reissuesNumber)
}