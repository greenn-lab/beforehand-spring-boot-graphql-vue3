export default {
  MENUS: `
query {
  menus {
    id
    upperId
    name
    nameAid
    uri
    description
    order
    badge
    icon
    attribute
    children {
      id
      name
      nameAid
      uri
      description
      order
      badge
      icon
      attribute
      children {
        id
        name
        nameAid
        uri
        description
        order
        badge
        icon
        attribute
        children {
          id
          name
          nameAid
          uri
          description
          order
          badge
          icon
          attribute
          children {
            id
            name
            nameAid
            uri
            description
            order
            badge
            icon
            attribute
            children {
              id
              name
              nameAid
              uri
              description
              order
              badge
              icon
              attribute
            }
          }
        }
      }
    }
  }
}
`
}
