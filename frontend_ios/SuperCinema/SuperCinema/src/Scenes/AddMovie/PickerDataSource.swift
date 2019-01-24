//
//  Created by Krzysztof Pawski on 23/01/2019.
//  Copyright © 2019 SuperCinemaSpZoo. All rights reserved.
//

import Foundation
import UIKit

final class PickerDataSource: NSObject, UIPickerViewDataSource, UIPickerViewDelegate {
    private weak var textField: UITextField?
    private let values: [String]

    init(values: [String], textField: UITextField?) {
        self.values = values
        self.textField = textField
    }

    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }

    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return values.count
    }

    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return values[row]
    }

    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        textField?.text = values[row]
    }
}
