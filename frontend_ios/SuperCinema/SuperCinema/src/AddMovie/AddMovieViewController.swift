//
//  Created by Krzysztof Pawski on 20/01/2019.
//  Copyright © 2019 SuperCinemaSpZoo. All rights reserved.
//

import UIKit

final class AddMovieViewController: UIViewController {

    @IBOutlet weak private var titleTextField: UITextField!
    @IBOutlet weak private var durationTextField: UITextField!
    @IBOutlet weak private var yearTextField: UITextField!
    @IBOutlet weak private var countryTextField: UITextField!

    override func viewDidLoad() {
        super.viewDidLoad()

        let doneMoviewItem = UIBarButtonItem(barButtonSystemItem: .done, target: self, action: #selector(addMovie))
        navigationItem.rightBarButtonItem = doneMoviewItem

        let cancelMovieItem = UIBarButtonItem(barButtonSystemItem: .cancel, target: self, action: #selector(cancel))
        navigationItem.leftBarButtonItem = cancelMovieItem
    }

    @objc private func addMovie() {
    }

    @objc private func cancel() {
        dismiss(animated: true, completion: nil)
    }

}

