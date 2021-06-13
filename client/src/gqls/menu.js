export default {
  MENUS: `
query {
  menus {
    id
    name
    uri
    upperId
    description
    icon
    classes
    badge
    children {
      id
      name
      uri
      description
      icon
      classes
      badge
      children {
        id
        name
        uri
        description
        icon
        classes
        badge
        children {
          id
          name
          uri
          description
          icon
          classes
          badge
          children {
            id
            name
            uri
            description
            icon
            classes
            badge
          }
        }
      }
    }
  }
}
`
}
