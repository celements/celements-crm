import { CelGoogleMapsViewer } from "./CelGoogleMapsViewer.mjs?version=202210100633";

const googleMapViewer = new CelGoogleMapsViewer();
if (['complete', 'interactive'].includes(document.readyState)) {
  console.debug("initGoogleMaps in dynamic mode loaded immediately", document.readyState);
  googleMapViewer.load();
} else {
  console.debug("initGoogleMaps in sync mode loaded on defer ", document.readyState);
  document.addEventListener('DOMContentLoaded', () => googleMapViewer.load());
}
