SUMMARY = "Murata Binaries"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://${S}/cyw-bt-patch/LICENCE.cypress;md5=cbc5f665d04f741f1e006d2096236ba7"

SRC_URI = " \
        git://github.com/murata-wireless/cyw-fmac-fw;protocol=http;branch=indrik;destsuffix=cyw-fmac-fw;name=cyw-fmac-fw \
        git://github.com/murata-wireless/cyw-fmac-nvram;protocol=http;branch=indrik;destsuffix=cyw-fmac-nvram;name=cyw-fmac-nvram \
        git://github.com/murata-wireless/cyw-bt-patch;protocol=http;branch=mickledore-indrik;destsuffix=cyw-bt-patch;name=cyw-bt-patch \
        git://github.com/murata-wireless/cyw-fmac-utils-imx32;protocol=http;branch=master;destsuffix=cyw-fmac-utils-imx32;name=cyw-fmac-utils-imx32 \
        git://github.com/murata-wireless/cyw-fmac-utils-imx64;protocol=http;branch=master;destsuffix=cyw-fmac-utils-imx64;name=cyw-fmac-utils-imx64 \
        git://github.com/project-chip/connectedhomeip;protocol=http;branch=master;destsuffix=connectedhomeip;name=connectedhomeip \
    	file://switch_module_imx6dlea-com.sh \
	    file://switch_module_imx6qea-com.sh \
        file://switch_module_imx6sxea-com.sh \
	    file://switch_module_imx6ulea-com.sh \
        file://switch_module_imx7dea-com.sh \
	    file://switch_module_imx7dea-ucom.sh \
        file://switch_module_imx7ulpea-ucom.sh \
        file://switch_module_imx8mmea-ucom.sh \
        file://switch_module_imx8mnea-ucom.sh \
        file://switch_module_imx8mm-influx.sh \
        file://switch_module_imx8mn-influx.sh \
        file://switch_module_imx8mqea-com.sh \
        file://switch_module_imx93ea-ucom.sh \
	    file://cyfmac55572-pcie.txt \
        file://cyfmac55572-sdio.txt \
        file://cyfmac55572-sdio-ifx-demo.txt \
        file://cyfmac55572-sdio_v2.4.3.txt \
        file://cyfmac55572-sdio_v2.5.1.txt \
        file://cyfmac55572-sdio-ifx-demo.txt \
	    file://CYW55560A1_001.002.087.0108.0000.sLNA.hcd \
	    file://CYW55560A1_001.002.087.0159.0010_wlcsp_iPA_sLNA_ANT0_Murata_Type2EA_FCC_max.hcd \
	    file://ot-daemon.64-bit \
	    file://ot-ctl.64-bit \
        file://fw_loader_imx_lnx.64-bit \
        file://ot-daemon.32-bit \
        file://ot-ctl.32-bit \
        file://WlanCalData_ext.conf \
        file://test_2el_spi.sh \
        file://mlanutl.32-bit \
        file://mlanutl.64-bit \
        file://load-fmac.sh \
        file://load-2ea-bt.sh \
        file://hostapd-wifi6.conf \
        file://wpa_supplicant-wifi6.conf \
        file://chip-tool \
        file://chip-tool-web \
        file://brcm_patchram_plus_usb_64bit \
        file://cyfmac4373-sdio_master_oob.txt \
"

SRCREV_nxp-linux-calibration="86290400930acaa239cbdd0d2f537de2bb9bca56"
SRCREV_cyw-fmac-fw="d6cd8b50b5f71ca3ba26fd88177676a688aac85b"
SRCREV_cyw-fmac-nvram="61b41349b5aa95227b4d2562e0d0a06ca97a6959"
SRCREV_cyw-bt-patch="d2a10c1ea528d8560a792b43cb51b29ccf494077"
SRCREV_cyw-fmac-utils-imx32="fcdd231e9bb23db3c93c10e5dff43a1182f220c5"
SRCREV_cyw-fmac-utils-imx64="52cc4cc6be8629781014505aa276b67e18cf6e8d"
SRCREV_connectedhomeip="7879111b8b17d5cb2789ffd4d634438dd2e8c52a"

SRCREV_default = "${AUTOREV}"

S = "${WORKDIR}"
B = "${WORKDIR}"
DEPENDS = " linux-firmware libnl wpa-supplicant cyw-supplicant"

do_compile () {
    echo "Compiling: "
    echo "Testing Make        Display:: ${MAKE}"
    echo "Testing bindir      Display:: ${bindir}"
    echo "Testing base_libdir Display:: ${base_libdir}"
    echo "Testing sysconfdir  Display:: ${sysconfdir}"
    echo "Testing S  Display:: ${S}"
    echo "Testing B  Display:: ${B}"
    echo "Testing D  Display:: ${D}"
    echo "WORK_DIR :: ${WORKDIR}"
	echo "MACHINE TYPE :: ${MACHINE}"
    echo "PWD :: "
    pwd
}

PACKAGES:prepend = "murata-binaries-wlarm "
FILES:murata-binaries-wlarm = "${bindir}/wlarm"

DO_INSTALL_64BIT_BINARIES = "no"
DO_INSTALL_64BIT_BINARIES_mx6 = "no"
DO_INSTALL_64BIT_BINARIES_mx7 = "no"
DO_INSTALL_64BIT_BINARIES_mx8 = "yes"
DO_INSTALL_64BIT_BINARIES_mx9 = "yes"

do_install () {
    echo "Installing: "
    install -d ${D}/lib/firmware/cypress
    install -d ${D}/lib/firmware/cypress/murata-master
    install -d ${D}/lib/firmware/brcm
    install -d ${D}/lib/firmware/brcm/murata-master
    install -d ${D}/usr/sbin
    install -d ${D}/etc/udev/rules.d
    install -d ${D}/usr/share/murata_wireless
    install -d ${D}/etc

    # Install /lib/firmware/nxp folder
    install -d ${D}/lib/firmware/nxp
    install -d ${D}/lib/firmware/nxp/murata
    install -d ${D}/lib/firmware/nxp/murata/files
	install -d ${D}/lib/firmware/nxp/murata/files/1XK
    install -d ${D}/lib/firmware/nxp/murata/files/1ZM
    install -d ${D}/lib/firmware/nxp/murata/files/1YM
    install -d ${D}/lib/firmware/nxp/murata/files/2DS
    install -d ${D}/lib/firmware/nxp/murata/files/1XL

#   Copying *.HCD files to etc/firmware and etc/firmware/murata-master (using "_" before the name of the file in murata-master)
    install -m 444 ${WORKDIR}/cyw-bt-patch/CYW4335C0.ZP.hcd ${D}/lib/firmware/brcm/BCM4335C0.ZP.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM4345C0_003.001.025.0187.0366.1MW.hcd ${D}/lib/firmware/brcm/BCM4345C0_003.001.025.0187.0366.1MW.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM43012C0_003.001.015.0303.0267.1LV.sAnt.hcd ${D}/lib/firmware/brcm/BCM43012C0_003.001.015.0303.0267.1LV.sAnt.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/CYW43012C1_003.002.024.0034.0004.2GF.hcd ${D}/lib/firmware/brcm/CYW43012C1_003.002.024.0034.0004.2GF.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/CYW43341B0.1BW.hcd ${D}/lib/firmware/brcm/BCM43341B0.1BW.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM43430A1_001.002.009.0159.0528.1DX.hcd ${D}/lib/firmware/brcm/BCM43430A1_001.002.009.0159.0528.1DX.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/CYW4350C0.1BB.hcd ${D}/lib/firmware/brcm/BCM4350C0.1BB.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM4356A2_001.003.015.0112.0410.1CX.hcd ${D}/lib/firmware/brcm/BCM4356A2_001.003.015.0112.0410.1CX.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM4359D0_004.001.016.0241.0275.1XA.sAnt.hcd ${D}/lib/firmware/brcm/BCM4359D0_004.001.016.0241.0275.1XA.sAnt.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM4373A0_001.001.025.0103.0155.FCC.CE.2AE.hcd ${D}/lib/firmware/brcm/BCM4373A0.2AE.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM4373A0_001.001.025.0103.0155.FCC.CE.2AE.hcd ${D}/lib/firmware/brcm/BCM4373A0.2BC.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/CYW4343A2_001.003.016.0031.0000.1YN.hcd ${D}/lib/firmware/brcm/CYW4343A2_001.003.016.0031.0000.1YN.hcd
    install -m 444 ${WORKDIR}/CYW55560A1_001.002.087.0159.0010_wlcsp_iPA_sLNA_ANT0_Murata_Type2EA_FCC_max.hcd ${D}/lib/firmware/brcm/CYW55560A1_001.002.087.0159.0010_wlcsp_iPA_sLNA_ANT0_Murata_Type2EA_FCC_max.hcd
    install -m 444 ${WORKDIR}/CYW55560A1_001.002.087.0159.0010_wlcsp_iPA_sLNA_ANT0_Murata_Type2EA_FCC_max.hcd ${D}/lib/firmware/brcm/BCM.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM4359D0_004.001.016.0241.0275.2BZ.sAnt.hcd ${D}/lib/firmware/brcm/BCM4359D0_004.001.016.0241.0275.2BZ.sAnt.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/README_BT_PATCHFILE.txt ${D}/lib/firmware/brcm/

    install -m 444 ${WORKDIR}/cyw-bt-patch/CYW4335C0.ZP.hcd    ${D}/lib/firmware/brcm/murata-master/_BCM4335C0.ZP.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM4345C0_003.001.025.0187.0366.1MW.hcd   ${D}/lib/firmware/brcm/murata-master/_BCM4345C0_003.001.025.0187.0366.1MW.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM43012C0_003.001.015.0303.0267.1LV.sAnt.hcd  ${D}/lib/firmware/brcm/murata-master/_BCM43012C0_003.001.015.0303.0267.1LV.sAnt.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM43012C0_003.001.015.0300.0266.1LV.dAnt.hcd ${D}/lib/firmware/brcm/murata-master/_BCM43012C0_003.001.015.0300.0266.1LV.dAnt.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/CYW43012C1_003.002.024.0034.0004.2GF.hcd ${D}/lib/firmware/brcm/murata-master/_CYW43012C1_003.002.024.0034.0004.2GF.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/CYW43341B0.1BW.hcd  ${D}/lib/firmware/brcm/murata-master/_BCM43341B0.1BW.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM43430A1_001.002.009.0159.0528.1DX.hcd  ${D}/lib/firmware/brcm/murata-master/_BCM43430A1_001.002.009.0159.0528.1DX.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/CYW4350C0.1BB.hcd   ${D}/lib/firmware/brcm/murata-master/_BCM4350C0.1BB.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM4356A2_001.003.015.0112.0410.1CX.hcd   ${D}/lib/firmware/brcm/murata-master/_BCM4356A2_001.003.015.0112.0410.1CX.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM4359D0_004.001.016.0241.0275.1XA.sAnt.hcd ${D}/lib/firmware/brcm/murata-master/_BCM4359D0_004.001.016.0241.0275.1XA.sAnt.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM4359D0_004.001.016.0241.0274.1XA.dAnt.hcd ${D}/lib/firmware/brcm/murata-master/_BCM4359D0_004.001.016.0241.0274.1XA.dAnt.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM4373A0_001.001.025.0103.0155.FCC.CE.2AE.hcd ${D}/lib/firmware/brcm/murata-master/_BCM4373A0.2AE.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM4373A0_001.001.025.0103.0155.FCC.CE.2AE.hcd ${D}/lib/firmware/brcm/murata-master/_BCM4373A0.2BC.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/CYW4343A2_001.003.016.0031.0000.1YN.hcd ${D}/lib/firmware/brcm/murata-master/_CYW4343A2_001.003.016.0031.0000.1YN.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM4359D0_004.001.016.0241.0275.2BZ.sAnt.hcd ${D}/lib/firmware/brcm/murata-master/_BCM4359D0_004.001.016.0241.0275.2BZ.sAnt.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/BCM4359D0_004.001.016.0241.0274.2BZ.dAnt.hcd ${D}/lib/firmware/brcm/murata-master/_BCM4359D0_004.001.016.0241.0274.2BZ.dAnt.hcd

#	Temporary from MMW
	install -m 444 ${WORKDIR}/CYW55560A1_001.002.087.0159.0010_wlcsp_iPA_sLNA_ANT0_Murata_Type2EA_FCC_max.hcd ${D}/lib/firmware/brcm/murata-master/_CYW55560A1_001.002.087.0159.0010_wlcsp_iPA_sLNA_ANT0_Murata_Type2EA_FCC_max.hcd
	install -m 444 ${WORKDIR}/CYW55560A1_001.002.087.0159.0010_wlcsp_iPA_sLNA_ANT0_Murata_Type2EA_FCC_max.hcd ${D}/lib/firmware/brcm/murata-master/_BCM.hcd
    install -m 444 ${WORKDIR}/cyw-bt-patch/README_BT_PATCHFILE.txt ${D}/lib/firmware/brcm/murata-master

#   Copying FW and CLM BLOB files (*.bin, *.clm_blob) to lib/firmware/cypress folder
	install -m 444 ${WORKDIR}/cyw-fmac-fw/*.bin ${D}/lib/firmware/cypress
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac43022-sdio.trxs ${D}/lib/firmware/cypress
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac55572-pcie.trxse ${D}/lib/firmware/cypress
    install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac55572-sdio.trxse ${D}/lib/firmware/cypress
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac4373-sdio.2AE.bin ${D}/lib/firmware/cypress/cyfmac4373-sdio.2AE.bin
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac4373-sdio.2BC.bin ${D}/lib/firmware/cypress/cyfmac4373-sdio.2BC.bin

#   Rename clm blob files accordingly
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac4354-sdio.1BB.clm_blob ${D}/lib/firmware/cypress/cyfmac4354-sdio.clm_blob
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac4356-pcie.1CX.clm_blob ${D}/lib/firmware/cypress/cyfmac4356-pcie.clm_blob
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac43012-sdio.1LV.clm_blob ${D}/lib/firmware/cypress/cyfmac43012-sdio.clm_blob
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac43022-sdio.2GF.STA.clm_blob ${D}/lib/firmware/cypress/cyfmac43022-sdio.clm_blob
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac43430-sdio.1DX.clm_blob ${D}/lib/firmware/cypress/cyfmac43430-sdio.clm_blob
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac43455-sdio.1MW.clm_blob ${D}/lib/firmware/cypress/cyfmac43455-sdio.clm_blob
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac4359-sdio.1WZ.clm_blob ${D}/lib/firmware/cypress/cyfmac4359-pcie.clm_blob
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac54591-pcie.1XA.clm_blob ${D}/lib/firmware/cypress/cyfmac54591-pcie.clm_blob
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac54591-sdio.2BZ.clm_blob ${D}/lib/firmware/cypress/cyfmac54591-sdio.clm_blob

# 	For 2AE and 1YN
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac4373-sdio.2AE.clm_blob ${D}/lib/firmware/cypress/cyfmac4373-sdio.clm_blob
	install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac43439-sdio.1YN.clm_blob  ${D}/lib/firmware/cypress/cyfmac43439-sdio.clm_blob 

#   For 2EA
    install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac55572-sdio.2EA.clm_blob  ${D}/lib/firmware/cypress/cyfmac55572-sdio.clm_blob
    install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac55572-pcie.2EA.clm_blob  ${D}/lib/firmware/cypress/cyfmac55572-pcie.clm_blob

	install -m 444 ${WORKDIR}/cyw-fmac-fw/README_CLM_BLOB.txt ${D}/lib/firmware/cypress/README_CLM_BLOB.txt
	install -m 444 ${WORKDIR}/cyw-fmac-fw/README_FW.txt ${D}/lib/firmware/cypress/README_FW.txt

#   Copying NVRAM files (*.txt) to lib/firmware/cypress and lib/firmware/cypress/murata-master
	install -m 444 ${WORKDIR}/cyw-fmac-nvram/*.txt ${D}/lib/firmware/cypress/murata-master

	install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac4339-sdio.ZP.txt ${D}/lib/firmware/cypress/cyfmac4339-sdio.txt
	install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac4354-sdio.1BB.txt ${D}/lib/firmware/cypress/cyfmac4354-sdio.txt
	install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac4356-pcie.1CX.txt ${D}/lib/firmware/cypress/cyfmac4356-pcie.txt
	install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac43012-sdio.1LV.txt ${D}/lib/firmware/cypress/cyfmac43012-sdio.txt
	install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac43022-sdio.2GF.txt ${D}/lib/firmware/cypress/cyfmac43022-sdio.txt
	install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac43340-sdio.1BW.txt ${D}/lib/firmware/cypress/cyfmac43340-sdio.txt
	install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac43362-sdio.SN8000.txt ${D}/lib/firmware/cypress/cyfmac43362-sdio.txt
	install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac43430-sdio.1DX.txt ${D}/lib/firmware/cypress/cyfmac43430-sdio.txt
	install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac43455-sdio.1MW.txt ${D}/lib/firmware/cypress/cyfmac43455-sdio.txt
	install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac54591-pcie.1XA.txt ${D}/lib/firmware/cypress/cyfmac54591-pcie.txt

#	For 2AE, 2BC, and 1YN
	install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac43439-sdio.1YN.txt ${D}/lib/firmware/cypress/cyfmac43439-sdio.txt

	install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac4373-sdio.2BC.txt ${D}/lib/firmware/cypress/cyfmac4373-sdio.2BC.txt
	install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac4373-sdio.2AE.txt ${D}/lib/firmware/cypress/cyfmac4373-sdio.2AE.txt

    install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac4373-sdio.2BC.bin ${D}/lib/firmware/cypress/cyfmac4373-sdio.2BC.bin
    install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac4373-sdio.2AE.bin ${D}/lib/firmware/cypress/cyfmac4373-sdio.2AE.bin
    install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac4373-usb.2BC.bin ${D}/lib/firmware/cypress/cyfmac4373-usb.2BC.bin
    install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac4373-usb.2AE.bin ${D}/lib/firmware/cypress/cyfmac4373-usb.2AE.bin

    install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac4373-sdio.2BC.clm_blob ${D}/lib/firmware/cypress/cyfmac4373-sdio.2BC.clm_blob
    install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac4373-sdio.2AE.clm_blob ${D}/lib/firmware/cypress/cyfmac4373-sdio.2AE.clm_blob

    install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac4373-sdio.2BC.txt      ${D}/lib/firmware/cypress/cyfmac4373-sdio.txt
    install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac4373-sdio.2BC.bin         ${D}/lib/firmware/cypress/cyfmac4373-sdio.bin
    install -m 444 ${WORKDIR}/cyw-fmac-fw/cyfmac4373-sdio.2BC.clm_blob    ${D}/lib/firmware/cypress/cyfmac4373-sdio.clm_blob

#	For 2EA 
	install -m 444 ${WORKDIR}/cyfmac55572-pcie.txt ${D}/lib/firmware/cypress
    install -m 444 ${WORKDIR}/cyfmac55572-sdio.txt ${D}/lib/firmware/cypress
    install -m 444 ${WORKDIR}/cyfmac55572-sdio-ifx-demo.txt ${D}/lib/firmware/cypress/murata-master
    install -m 444 ${WORKDIR}/cyfmac55572-sdio_v2.4.3.txt ${D}/lib/firmware/cypress/murata-master
    install -m 444 ${WORKDIR}/cyfmac55572-sdio_v2.5.1.txt ${D}/lib/firmware/cypress/murata-master

#   For 2BZ
	install -m 444 ${WORKDIR}/cyw-fmac-nvram/cyfmac54591-sdio.2ant.2BZ.txt ${D}/lib/firmware/cypress/cyfmac54591-sdio.txt

	install -m 444 ${WORKDIR}/cyw-fmac-nvram/README_NVRAM.txt ${D}/lib/firmware/cypress

	# Added Calibration configuration file for 1YM(NXP)
#	install -m 444 ${WORKDIR}/10-network.rules                  ${D}${sysconfdir}/udev/rules.d/10-network.rules
	install -m 444 ${WORKDIR}/hostapd-wifi6.conf                ${D}${sysconfdir}/hostapd-wifi6.conf
	install -m 444 ${WORKDIR}/wpa_supplicant-wifi6.conf         ${D}${sysconfdir}/wpa_supplicant-wifi6.conf

	install -d ${D}/lib/firmware/nxp
    install -d ${D}/usr/share/nxp_wireless

#   Copying wl tool binary to /usr/sbin
    if [ ${TARGET_ARCH} = "aarch64" ]; then
		install -m 755 ${WORKDIR}/cyw-fmac-utils-imx64/wl ${D}/usr/sbin/wl
		install -m 755 ${WORKDIR}/ot-ctl.64-bit ${D}/usr/sbin/ot-ctl
		install -m 755 ${WORKDIR}/ot-daemon.64-bit ${D}/usr/sbin/ot-daemon
		install -m 755 ${WORKDIR}/fw_loader_imx_lnx.64-bit ${D}/usr/sbin/fw_loader_imx_lnx
		install -m 755 ${WORKDIR}/mlanutl.64-bit ${D}/usr/share/nxp_wireless/mlanutl
	else
		install -m 755 ${WORKDIR}/cyw-fmac-utils-imx32/wl ${D}/usr/sbin/wl
		install -m 755 ${WORKDIR}/ot-ctl.32-bit ${D}/usr/sbin/ot-ctl
		install -m 755 ${WORKDIR}/ot-daemon.32-bit ${D}/usr/sbin/ot-daemon
		install -m 755 ${WORKDIR}/mlanutl.32-bit ${D}/usr/share/nxp_wireless/mlanutl
	fi
	
#	Points default to NXP
	ln -sf /usr/sbin/wpa_supplicant.nxp ${D}${sbindir}/wpa_supplicant
    ln -sf /usr/sbin/wpa_cli.nxp ${D}${sbindir}/wpa_cli
	ln -sf /usr/sbin/hostapd.nxp ${D}${sbindir}/hostapd
	ln -sf /usr/sbin/hostapd_cli.nxp ${D}${sbindir}/hostapd_cli

#	Based on MACHINE type
#	echo "DEBUG:: MACHINE TYPE :: ${MACHINE}"
	# Added Script file for switching between CYW and NXP
	case ${MACHINE} in
	  imx6dlea-com)
		install -m 755 ${S}/switch_module_imx6dlea-com.sh ${D}/usr/sbin/switch_module.sh
		;;
	  imx6qea-com)
		install -m 755 ${S}/switch_module_imx6qea-com.sh ${D}/usr/sbin/switch_module.sh
		;;
	  imx6sxea-com)
		install -m 755 ${S}/switch_module_imx6sxea-com.sh ${D}/usr/sbin/switch_module.sh
		;;
 	  imx6ulea-com)
		install -m 755 ${S}/switch_module_imx6ulea-com.sh ${D}/usr/sbin/switch_module.sh
		;;
	  imx7dea-com)
		install -m 755 ${S}/switch_module_imx7dea-com.sh ${D}/usr/sbin/switch_module.sh
		;;
	  imx7dea-ucom)
		install -m 755 ${S}/switch_module_imx7dea-ucom.sh ${D}/usr/sbin/switch_module.sh
		;;
	  imx7ulpea-ucom)
		install -m 755 ${S}/switch_module_imx7ulpea-ucom.sh ${D}/usr/sbin/switch_module.sh
		;;
	  imx8mmea-ucom)
		install -m 755 ${S}/switch_module_imx8mmea-ucom.sh ${D}/usr/sbin/switch_module.sh
		;;
	  imx8mnea-ucom)
		install -m 755 ${S}/switch_module_imx8mnea-ucom.sh ${D}/usr/sbin/switch_module.sh
		;;
	  imx8mqea-com)
		install -m 755 ${S}/switch_module_imx8mqea-com.sh ${D}/usr/sbin/switch_module.sh
		;;
	  imx93ea-ucom)
		install -m 755 ${S}/switch_module_imx93ea-ucom.sh ${D}/usr/sbin/switch_module.sh
		;;
      imx8mm-influx-rex-smart)
		install -m 755 ${S}/switch_module_imx8mm-influx.sh ${D}/usr/sbin/switch_module.sh
		;;
      imx8mn-influx-rex-smart)
		install -m 755 ${S}/switch_module_imx8mn-influx.sh ${D}/usr/sbin/switch_module.sh
		;;
	esac

#	Install nxp linux calibration files
	install -m 444 ${WORKDIR}/nxp-linux-calibration/murata/files/1XK/* ${D}/lib/firmware/nxp/murata/files/1XK
	install -m 444 ${WORKDIR}/nxp-linux-calibration/murata/files/1YM/* ${D}/lib/firmware/nxp/murata/files/1YM
	install -m 444 ${WORKDIR}/nxp-linux-calibration/murata/files/1ZM/* ${D}/lib/firmware/nxp/murata/files/1ZM
	install -m 444 ${WORKDIR}/nxp-linux-calibration/murata/files/2DS/* ${D}/lib/firmware/nxp/murata/files/2DS
	install -m 444 ${WORKDIR}/nxp-linux-calibration/murata/files/1XL/* ${D}/lib/firmware/nxp/murata/files/1XL


	install -m 444 ${WORKDIR}/nxp-linux-calibration/murata/files/bt_power_config_1.sh ${D}/lib/firmware/nxp/murata/files
    install -m 777 ${WORKDIR}/nxp-linux-calibration/murata/files/wifi_mod_para_murata.conf ${D}/lib/firmware/nxp/murata/files
    install -m 755 ${WORKDIR}/nxp-linux-calibration/murata/switch_regions.sh ${D}/usr/sbin/switch_regions.sh
    install -m 444 ${WORKDIR}/nxp-linux-calibration/murata/README.txt ${D}/lib/firmware/nxp/murata/README.txt

#	Copy configuration file for 2EL
	install -m 444 ${WORKDIR}/WlanCalData_ext.conf ${D}/lib/firmware/nxp
    install -m 755 ${WORKDIR}/test_2el_spi.sh ${D}/usr/sbin/test_2el_spi.sh
	install -m 755 ${WORKDIR}/load-fmac.sh ${D}/usr/share/murata_wireless/load-fmac.sh
	install -m 755 ${WORKDIR}/load-2ea-bt.sh ${D}/usr/sbin/load-2ea-bt.sh


	install -m 755 ${WORKDIR}/chip-tool ${D}/usr/sbin/chip-tool
	install -m 755 ${WORKDIR}/chip-tool-web ${D}/usr/sbin/chip-tool-web
	install -m 755 ${WORKDIR}/connectedhomeip/credentials/production/paa-root-certs/* ${D}/usr/share/murata_wireless
	install -m 755 ${WORKDIR}/brcm_patchram_plus_usb_64bit ${D}/usr/sbin/brcm_patchram_plus_usb_64bit
    install -m 755 ${S}/cyfmac4373-sdio_master_oob.txt ${D}/lib/firmware/cypress/murata-master/cyfmac4373-sdio_master_oob.txt
}

PACKAGES =+ "${PN}-mfgtest"

FILES:${PN} += "/lib/firmware"
FILES:${PN} += "/lib/firmware/*"
FILES:${PN} += "/lib/firmware/brcm"
FILES:${PN} += "/lib/firmware/brcm/murata-master"
FILES:${PN} += "usr/share/nxp_wireless"
FILES:${PN} += "usr/share/murata_wireless"

FILES:${PN} += "${bindir}"
FILES:${PN} += "${sbindir}"
FILES:${PN} += "{sysconfdir}/firmware"
FILES:${PN} += "/lib"
FILES:${PN} += "{sysconfdir}/firmware/nxp"

FILES:${PN}-mfgtest = " \
	/usr/bin/wl \
"

INSANE_SKIP:${PN} += "build-deps"
INSANE_SKIP:${PN} += "file-rdeps"
INSANE_SKIP:${PN} += "already-stripped"
