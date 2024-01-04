package app.revanced.patches.myfitnesspal

import app.revanced.patches.myfitnesspal.fingerprints.PremiumRepositoryImpl_isPremiumUserFingerprint
import app.revanced.patches.myfitnesspal.fingerprints.PremiumRepositoryImpl_getFeatureAvailabilityFingerprint
import app.revanced.patches.myfitnesspal.fingerprints.PremiumRepositoryImpl_isFeatureSubscribedFingerprint
import app.revanced.patches.myfitnesspal.fingerprints.PremiumRepositoryImpl_isPremiumFeatureAvailableFingerprint
import app.revanced.patches.myfitnesspal.fingerprints.PremiumRepositoryImpl_isPremiumFeatureAvailableByIdFingerprint
import app.revanced.patches.myfitnesspal.fingerprints.PremiumRepositoryImpl_isPremiumFeatureSubscribedFingerprint
import app.revanced.patches.myfitnesspal.fingerprints.PremiumRepositoryImpl_isPremiumFeatureSubscribedByIdFingerprint

import app.revanced.patcher.patch.PatchException
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.replaceInstructions;


// Imports

@Patch(
        name = "Unlock Premium",
        description = "Enables some Premium functionality.",
        compatiblePackages = [CompatiblePackage("com.myfitnesspal.android")]
)
object PremiumPatch : BytecodePatch(setOf(
    PremiumRepositoryImpl_isPremiumUserFingerprint,
    PremiumRepositoryImpl_getFeatureAvailabilityFingerprint,
    PremiumRepositoryImpl_isFeatureSubscribedFingerprint,
    PremiumRepositoryImpl_isPremiumFeatureAvailableFingerprint,
    PremiumRepositoryImpl_isPremiumFeatureSubscribedFingerprint,
    PremiumRepositoryImpl_isPremiumFeatureSubscribedByIdFingerprint,
    PremiumRepositoryImpl_isPremiumFeatureAvailableByIdFingerprint
)) {
    override fun execute(context: BytecodeContext) {

        // all of these functions just need to return true
        val simpleBools = setOf(
            PremiumRepositoryImpl_isPremiumUserFingerprint,
            PremiumRepositoryImpl_isFeatureSubscribedFingerprint,
            PremiumRepositoryImpl_isPremiumFeatureAvailableFingerprint,
            PremiumRepositoryImpl_isPremiumFeatureSubscribedFingerprint,
            PremiumRepositoryImpl_isPremiumFeatureSubscribedByIdFingerprint,
            PremiumRepositoryImpl_isPremiumFeatureAvailableByIdFingerprint
        )
        for (item in simpleBools) {
            item.result?.mutableMethod?.apply {
                replaceInstructions(
                    0,
                    """
                        const/4 v0, 1
                        return v0
                    """
                )
            } ?: throw PatchException("PremiumRepositoryImpl fingerprint not found")
        }

        // this one needs to return an enum
        PremiumRepositoryImpl_getFeatureAvailabilityFingerprint.result?.mutableMethod?.apply {
            replaceInstructions(
                0,
                """
                    const-string v0, "feature"
                    sget-object v0, Lcom/myfitnesspal/premium/data/FeatureAvailability;->Available:Lcom/myfitnesspal/premium/data/FeatureAvailability;
                    return-object v0
                """
            )
        } ?: throw PatchException("getFeatureAvailability fingerprint not found!")
    }
}
