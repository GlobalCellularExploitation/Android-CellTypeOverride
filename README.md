Android-CellTypeOverride
============================

Simulates different aspects of a cellular environment. System-wide override of the Network and Phone type for Android.

Description
-----------

This tool simulates different aspects of a cellular environment by effectively overriding the Network and Phone type for all Android applications on a device. The tool hooks specific methods that system applications may use to determine the network and phone type.

The tool can be used to make an application believes the phone is using a GSM/LTE/CDMA network when it has no SIM card and is only connected over Wifi, for example. This can tremendously help testing cellular specific code.

The concept for this tool was used - on a more granular level - to find and exploit flaws in manufacturers/cariers applications on Android devices. The research was presented at BlackHat 2014: https://www.blackhat.com/us-14/briefings.html#cellular-exploitation-on-a-global-scale-the-rise-and-fall-of-the-control-protocol.

Usage
-----

* This tool comprises 4 Cydia Substrate extensions used to override the network/phone types. See the following binaries:
  * Android-CellTypeOverride_NONE.apk
  * Android-CellTypeOverride_GSM.apk
  * Android-CellTypeOverride_CDMA.apk
  * Android-CellTypeOverride_LTE.apk

* Ensure that Cydia Substrate has been deployed on your test device. The installer requires a rooted device and can be found on the Google Play store at https://play.google.com/store/apps/details?id=com.saurik.substrate&hl=en 
* Install the APK package corresponding to the network you want to use on the device:

        adb install Android-OverrideCellType_NETWORK_TYPE.apk

Notes
-----
* This should only be used on test devices. Please ensure you properly backup your device before installing this tool. It has only been tested on specific types of devices, and it may break with non-tested carriers/manufacturers.

Author
-------
Marc Blanchou

Contributor
-------
Mathew Solnik
