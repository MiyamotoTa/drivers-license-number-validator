package takahiro.miyamoto.dlnv.application

import takahiro.miyamoto.dlnv.domain.model.license.LicenseNumber

/**
 * 免許証番号検証サービス
 * @author miyamoto_ta
 */
interface NumberValidationService {
    /**
     * 免許証番号を検証する.
     * @param licenseNumber 免許証番号
     * @return 検証した結果、問題がなければTrueを返す
     */
    fun validate(licenseNumber: LicenseNumber): Boolean
}