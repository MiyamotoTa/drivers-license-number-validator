package takahiro.miyamoto.dlnv.application.impl

import takahiro.miyamoto.dlnv.application.NumberValidationService
import takahiro.miyamoto.dlnv.domain.model.license.LicenseNumber

/**
 * @author miyamoto_ta
 */
class NumberValidationServiceImpl : NumberValidationService {
    override fun validate(licenseNumber: LicenseNumber): Boolean {
        if (!validateLicenseAcquisitionYear(licenseNumber)) {
            return false
        }

        if (!validateControlNumber(licenseNumber)) {
            return false
        }

        if (!validateCheckDigit(licenseNumber)) {
            return false
        }

        if (!validateReissuesNumber(licenseNumber)) {
            return false
        }
        return true
    }

    /**
     * 免許証取得年が有効範囲内かどうかを検証する
     *
     * @param licenseNumber 免許証番号
     * @return 免許証取得年が有効であればTrueを返す
     */
    private fun validateLicenseAcquisitionYear(licenseNumber: LicenseNumber): Boolean = licenseNumber.licenseAcquisitionYear in 0..99

    /**
     * 免許証管理番号が有効範囲内かどうかを検証する
     *
     * @param licenseNumber 免許証番号
     * @return 免許証管理番号が有効であればTrueを返す
     */
    private fun validateControlNumber(licenseNumber: LicenseNumber) = licenseNumber.controlNumber in 0..999999

    /**
     * チェックディジットが有効化どうかを検証する
     *
     * @param licenseNumber 免許証番号
     * @return チェックディジットが正しければTrueを返す
     */
    private fun validateCheckDigit(licenseNumber: LicenseNumber): Boolean {
        val pscId = licenseNumber.publicSafetyCommission.id
        val year = licenseNumber.licenseAcquisitionYear
        val controlNumber = licenseNumber.controlNumber

        val checkValue = pscId.format("%02d") + year.format("%02d") + controlNumber.format("%06d")

        return licenseNumber.checkDigit == calcCheckDigit(checkValue.toLong())
    }

    private fun Int.format(arg: String) = String.format(arg, this)

    private fun calcCheckDigit(number: Long): Int {
        val reversed = number.toString().reversed().toList().map {
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

    private fun validateReissuesNumber(licenseNumber: LicenseNumber) = licenseNumber.reissuesNumber in 0..9
}