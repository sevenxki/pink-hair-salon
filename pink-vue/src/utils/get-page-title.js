import defaultSettings from '@/settings'

const title = defaultSettings.title || 'Pink Hair Salon'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
