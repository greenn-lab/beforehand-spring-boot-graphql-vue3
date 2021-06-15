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
    branches {
      id
      name
      uri
      description
      icon
      classes
      badge
      branches {
        id
        name
        uri
        description
        icon
        classes
        badge
        branches {
          id
          name
          uri
          description
          icon
          classes
          badge
          branches {
            id
            name
            uri
            description
            icon
            classes
            badge
            branches {
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
}
`
}
