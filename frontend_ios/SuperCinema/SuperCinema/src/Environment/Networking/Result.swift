//
//  Created by Krzysztof Pawski on 22/01/2019.
//  Copyright © 2019 SuperCinemaSpZoo. All rights reserved.
//

import Foundation

enum Result<T> {
    case succes(T)
    case error(Error)
}

typealias ResultBlock = (Result<Any>) -> Void
