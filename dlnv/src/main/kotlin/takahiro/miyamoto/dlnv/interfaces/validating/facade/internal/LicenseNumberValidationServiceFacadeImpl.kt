package takahiro.miyamoto.dlnv.interfaces.validating.facade.internal

import org.springframework.stereotype.Component
import takahiro.miyamoto.dlnv.application.LicenseNumberValidationService
import takahiro.miyamoto.dlnv.domain.model.license.LicenseNumber
import takahiro.miyamoto.dlnv.interfaces.validating.facade.LicenseNumberValidationServiceFacade

/**
 * @author miyamoto_ta
 */
@Component
class LicenseNumberValidationServiceFacadeImpl(
        private val licenseNumberValidationService: LicenseNumberValidationService
) : LicenseNumberValidationServiceFacade {
    override fun validateLicense(licenseNumber: String): Boolean {
        if (!LicenseNumber.validateFormat(licenseNumber)) {
            return false
        }
        val publicSafetyCommissionId: Int = licenseNumber.substring(0..1).toInt()
        val licenseAcquisitionYear: Int = licenseNumber.substring(2..3).toInt()
        val controlNumber: Int = licenseNumber.substring(4..9).toInt()
        val checkDigit: Int = licenseNumber.substring(10..10).toInt()
        val reissuesNumber: Int = licenseNumber.substring(11..11).toInt()
        return licenseNumberValidationService.validate(publicSafetyCommissionId, licenseAcquisitionYear, controlNumber, checkDigit, reissuesNumber)
    }

}