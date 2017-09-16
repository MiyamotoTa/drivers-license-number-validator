package takahiro.miyamoto.dlnv.domain.model.license

/**
 * 日本の免許証番号の12桁の数値を意味別に分類したクラス.
 * @property publicSafetyCommission 都道府県公安委員会
 * @property licenseAcquisitionYear 免許証取得年の下二桁
 * @property controlNumber 各公安委員会によって管理されている番号
 * @property checkDigit チェックディジット
 * @property reissuesNumber 免許証再発行回数
 *
 * @author miyamoto_ta
 */
data class LicenseNumber(
        val publicSafetyCommission: PublicSafetyCommission,
        val licenseAcquisitionYear: Int,
        val controlNumber: Int,
        val checkDigit: Int,
        val reissuesNumber: Int
)