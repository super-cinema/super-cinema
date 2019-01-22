//
//  Created by Krzysztof Pawski on 22/01/2019.
//  Copyright © 2019 SuperCinemaSpZoo. All rights reserved.
//

import Foundation

struct Environment {
    private(set) var dataProvider = Api()
    private(set) var userDefaults = UserDefaults.standard
}

var Current = Environment()
