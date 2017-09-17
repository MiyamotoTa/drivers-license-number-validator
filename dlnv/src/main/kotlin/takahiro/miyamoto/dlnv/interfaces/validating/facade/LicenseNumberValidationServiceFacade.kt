package takahiro.miyamoto.dlnv.interfaces.validating.facade

/**
 * @author miyamoto_ta
 */
interface LicenseNumberValidationServiceFacade {
    /**
     * 免許証番号を検証する
     * @param licenseNumber 免許証番号
     */
    fun validateLicense(licenseNumber: String): Boolean
}