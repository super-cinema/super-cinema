//
//  Created by Krzysztof Pawski on 20/01/2019.
//  Copyright © 2019 SuperCinemaSpZoo. All rights reserved.
//

import Foundation

struct Movie: Codable {
    let id: Int?
    let title: String
    let duration: Int
    let productionCountry: String?
    let productionYear: Int?
    let directors: [Crew]
    let cast: [Crew]?
    let movieShow: String?
    let types: [String]

    init(id: Int? = nil,
         title: String,
         duration: Int,
         productionCountry: String? = nil,
         productionYear: Int? = nil,
         directors: [Crew],
         cast: [Crew]? = nil,
         movieShow: String? = nil,
         types: [String]) {
        self.id = id
        self.title = title
        self.duration = duration
        self.productionCountry = productionCountry
        self.productionYear = productionYear
        self.directors = directors
        self.cast = cast
        self.movieShow = movieShow
        self.types = types
    }

    var details: String {
        var detailsElements: [String] = []
        if let country = productionCountry {
            detailsElements.append(country)
        }
        if let year = productionYear {
            detailsElements.append(String(year))
        }
        return detailsElements.compactMap({ $0 }).joined(separator: ", ")
    }
}

struct Crew: Codable {
}
