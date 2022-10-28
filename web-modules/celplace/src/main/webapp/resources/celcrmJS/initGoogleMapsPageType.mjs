import { CelGoogleMapsViewer } from "./CelGoogleMapsViewer.mjs?version=202210100633";

const onReady = callback => (document.readyState === 'loading')
    ? document.addEventListener('DOMContentLoaded', callback)
    : callback();

onReady(() => new CelGoogleMapsViewer().load());
