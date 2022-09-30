const version = new URL(import.meta.url).searchParams.get('someURLInfo')
console.log("initGoogleMapsPageType.js version ", version);

import CelGoogleMapsViewer from ("./CelGoogleMapsViewer.mjs?version=" + version);

if (['complete', 'interactive'].includes(document.readyState)) {
  console.debug("initGoogleMaps in dynamic mode loaded immediately", document.readyState);
  new CelGoogleMapsViewer().load();
} else {
  console.debug("initGoogleMaps in sync mode loaded on defer ", document.readyState);
  const mapsViewer = new CelGoogleMapsViewer();
  document.addEventListener('DOMContentLoaded', () => mapsViewer.load());
}
