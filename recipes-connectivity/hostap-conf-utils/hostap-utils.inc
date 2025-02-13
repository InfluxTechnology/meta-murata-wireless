SUMMARY = "User mode helpers for the hostap driver"
DESCRIPTION = "The hostap driver supports Host AP mode, it allows for IEEE 802.11 \
management functions on the host computer and allows the system to act as an access point."
HOMEPAGE = "https://w1.fi"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
			file://util.c;beginline=1;endline=9;md5=d3b9280851302e5ba34e5fb717489b6d"
SECTION = "kernel/userland"
PR = "r4"

SRC_URI = "https://w1.fi/releases/hostap-utils-${PV}.tar.gz \
           file://hostap-fw-load.patch \
           file://0001-Define-_u32-__s32-__u16-__s16-__u8-in-terms-of-c99-t.patch \
"
S = "${WORKDIR}/hostap-utils-${PV}"

BINARIES = "hostap_crypt_conf hostap_diag hostap_fw_load hostap_io_debug \
	    hostap_rid prism2_param prism2_srec split_combined_hex"

do_install() {
	install -d ${D}${sbindir}/
	for f in ${BINARIES}
	do
		install -m 0755 $f ${D}${sbindir}/
	done
}

