require chromium-gn.inc

SRC_URI += " \
    git://github.com/Igalia/cef.git;protocol=https;destsuffix=${WORKDIR}/cef;branch=${BRANCH_cef};rev=${SRCREV_cef};name=cef \
    file://0001-Add-missing-algorithm-header-in-bitmap_cursor_factor.patch \
    file://0001-ozone-wayland-do-not-use-modifiers-for-linear-buffer.patch \
"

BRANCH_cef = "adunaev@3987"
SRCREV_cef = "14acbb12936432bc3ee74c8c19b77f3edc7ab301"

REQUIRED_DISTRO_FEATURES = "wayland"

DEPENDS += "\
        libxkbcommon \
        virtual/egl \
        wayland \
        wayland-native \
"

GN_ARGS += "\
        ${PACKAGECONFIG_CONFARGS} \
        use_ozone=true \
        ozone_auto_platforms=false \
        ozone_platform_headless=true \
        ozone_platform_wayland=true \
        ozone_platform_x11=false \
        system_wayland_scanner_path="${STAGING_BINDIR_NATIVE}/wayland-scanner" \
        use_xkbcommon=true \
        use_system_libwayland=true \
        use_system_minigbm=true \
        use_system_libdrm=true \
        use_gtk=false \
"

# The chromium binary must always be started with those arguments.
CHROMIUM_EXTRA_ARGS_append = " --ozone-platform=wayland"
