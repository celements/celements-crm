import CelGoogleMapsViewer from "./CelGoogleMapsViewer.mjs?version=202209301438";

if (['complete', 'interactive'].includes(document.readyState)) {
  console.debug("initGoogleMaps in dynamic mode loaded immediately", document.readyState);
  new CelGoogleMapsViewer().load();
} else {
  console.debug("initGoogleMaps in sync mode loaded on defer ", document.readyState);
  const mapsViewer = new CelGoogleMapsViewer();
  document.addEventListener('DOMContentLoaded', () => mapsViewer.load());
}
