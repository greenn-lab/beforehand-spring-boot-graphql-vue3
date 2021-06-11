export default {
  MENUS: `
query {
  menus {
    id
    name
    nameEn
    uri
    description
    children {
      id
      name
      children {
        id
        name
        children {
          id
          name
          children {
            id
            name
          }
        }
      }
    }
  }
}
`
}
