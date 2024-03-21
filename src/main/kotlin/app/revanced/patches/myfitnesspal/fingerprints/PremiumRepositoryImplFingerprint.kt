package app.revanced.patches.myfitnesspal.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

object PremiumRepositoryImpl_isPremiumUserFingerprint :
                MethodFingerprint(
                                returnType = "Z",
                                accessFlags = 1,
                                customFingerprint = { methodDef, _ ->
                                        methodDef.definingClass.endsWith(
                                                        "PremiumRepositoryImpl;"
                                        ) && methodDef.name == "isPremiumUser"
                                }
                )

object PremiumRepositoryImpl_getFeatureAvailabilityFingerprint :
                MethodFingerprint(
                                accessFlags = 1,
                                customFingerprint = { methodDef, _ ->
                                        methodDef.definingClass.endsWith(
                                                        "PremiumRepositoryImpl;"
                                        ) && methodDef.name == "getFeatureAvailability"
                                }
                )

object PremiumRepositoryImpl_isFeatureSubscribedFingerprint :
                MethodFingerprint(
                                returnType = "Z",
                                accessFlags = 1,
                                customFingerprint = { methodDef, _ ->
                                        methodDef.definingClass.endsWith(
                                                        "PremiumRepositoryImpl;"
                                        ) && methodDef.name == "isFeatureSubscribed"
                                }
                )

object PremiumRepositoryImpl_isPremiumFeatureAvailableFingerprint :
                MethodFingerprint(
                                returnType = "Z",
                                accessFlags = 1,
                                customFingerprint = { methodDef, _ ->
                                        methodDef.definingClass.endsWith(
                                                        "PremiumRepositoryImpl;"
                                        ) && methodDef.name == "isPremiumFeatureAvailable"
                                }
                )
