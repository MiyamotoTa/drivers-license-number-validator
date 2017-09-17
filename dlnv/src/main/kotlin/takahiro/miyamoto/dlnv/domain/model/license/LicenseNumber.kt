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
        private val publicSafetyCommission: PublicSafetyCommission,
        private val licenseAcquisitionYear: Int,
        private val controlNumber: Int,
        private val checkDigit: Int,
        private val reissuesNumber: Int
) {
    /**
     * 免許証取得年が有効範囲内かどうかを検証する
     *
     * @return 免許証取得年が有効であればTrueを返す
     */
    fun validateLicenseAcquisitionYear(): Boolean = licenseAcquisitionYear in 0..99

    /**
     * 免許証管理番号が有効範囲内かどうかを検証する
     *
     * @return 免許証管理番号が有効であればTrueを返す
     */
    fun validateControlNumber() = controlNumber in 0..999999

    /**
     * チェックディジットが有効化どうかを検証する
     *
     * @return チェックディジットが正しければTrueを返す
     */
    fun validateCheckDigit(): Boolean {
        val checkValue = publicSafetyCommission.id.format("%02d") + licenseAcquisitionYear.format("%02d") + controlNumber.format("%06d")

        return checkDigit == calcCheckDigit(checkValue.toLong())
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

    fun validateReissuesNumber() = reissuesNumber in 0..9
}