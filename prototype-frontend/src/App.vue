<template>
	<div :class="containerClass">
		<div class="layout-main-container">
			<div class="layout-main">
				<router-view />
			</div>
			<AppFooter />
		</div>
	</div>
</template>

<script>
import AppFooter from './components/AppFooter.vue';

export default {
	components: {
		'AppFooter': AppFooter,
	},
	data() {
		return {
			// the values of these variables define
			// what css classes are applied to main div
			// defines responsive behavior
			layoutMode: "static", // values: static, overlay
			staticMenuInactive: true,
			overlayMenuActive: false,
			mobileMenuActive: false,
		};
	},
	computed: {
		containerClass() {
			return ["layout-wrapper", {
				"layout-overlay": this.layoutMode === "overlay",
				"layout-static": this.layoutMode === "static",
				"layout-static-sidebar-inactive": this.staticMenuInactive && this.layoutMode === "static",
				"layout-overlay-sidebar-active": this.overlayMenuActive && this.layoutMode === "overlay",
				"layout-mobile-sidebar-active": this.mobileMenuActive,
				"p-input-filled": this.$primevue.config.inputStyle === "filled",
				"p-ripple-disabled": this.$primevue.config.ripple === false,
			}];
		},
	},
};
</script>