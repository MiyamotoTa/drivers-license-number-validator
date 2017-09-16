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

        return true
    }

    /**
     * 免許証取得年が有効範囲内かどうかを検証する
     *
     * @param licenseNumber 免許証番号
     * @return 免許証取得年が有効であればTrueを返す
     */
    private fun validateLicenseAcquisitionYear(licenseNumber: LicenseNumber): Boolean = licenseNumber.licenseAcquisitionYear in 0..99
}