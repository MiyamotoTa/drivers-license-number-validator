package takahiro.miyamoto.dlnv.application

/**
 * 免許証番号検証サービス
 * @author miyamoto_ta
 */
interface LicenseNumberValidationService {
    /**
     * 免許証番号を検証する.
     * @property publicSafetyCommissionId 都道府県公安委員会
     * @property licenseAcquisitionYear 免許証取得年の下二桁
     * @property controlNumber 各公安委員会によって管理されている番号
     * @property checkDigit チェックディジット
     * @property reissuesNumber 免許証再発行回数
     * @return 検証した結果、問題がなければTrueを返す
     */
    fun validate(
            publicSafetyCommissionId: Int,
            licenseAcquisitionYear: Int,
            controlNumber: Int,
            checkDigit: Int,
            reissuesNumber: Int
    ): Boolean
}