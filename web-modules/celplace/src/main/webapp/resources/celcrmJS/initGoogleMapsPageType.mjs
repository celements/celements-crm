import { CEL_GOOGLE_MAPS_STYLER, CelGoogleMapsViewer } from "./CelGoogleMapsViewer.mjs?version=202210100633";

const options = { styleTemplate : 'modernBlackWhite' };
const googleMapViewer = new CelGoogleMapsViewer(options);
if (['complete', 'interactive'].includes(document.readyState)) {
  console.debug("initGoogleMaps in dynamic mode loaded immediately", document.readyState);
  googleMapViewer.load();
} else {
  console.debug("initGoogleMaps in sync mode loaded on defer ", document.readyState);
  document.addEventListener('DOMContentLoaded', () => googleMapViewer.load());
}
