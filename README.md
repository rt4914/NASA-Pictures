# NASA Pictures

## Task
### Images Grid Screen
This is the home screen. When it launches, show a scrollable grid of pictures starting with the latest images first. When the user taps on an image that should open the image detail screen.

### Image Detail Screen
This screen displays the full size image along with the metadata like title, description, etc. The user should also be able to swipe through images.

## Architecture
### Activity
In this project activities are used for intialising the UI and to route between different activities.

### Presenter
In this business logic is mentioned and code to connnect `Activity` to `ViewModel`.

### ViewModel
Interacting with UI and setting data to UI is done via ViewModels.

### Interface
I have used interface to send information to activities correctly.

## Output
<img src="https://user-images.githubusercontent.com/9396084/113513160-db750380-9585-11eb-96eb-9a68a5040922.png" height="250"> <img src="https://user-images.githubusercontent.com/9396084/113513264-7f5eaf00-9586-11eb-831e-904f95a9b298.png" height = "250">

<img src="https://user-images.githubusercontent.com/9396084/113513171-e4fe6b80-9585-11eb-9a8c-13fba72f8611.png" width = "250"> <img src="https://user-images.githubusercontent.com/9396084/113513168-e334a800-9585-11eb-8e6c-4588f8c5321b.png" width = "250">

[Video Output](https://drive.google.com/file/d/1TWsct7y1HHQKthQtT5jGZhfWnDLyqjdp/view?usp=sharing)

[Accessibility Scanner Pass Output](https://drive.google.com/file/d/1fu7QomJJ71uLiLBHi96MW6IpmcUvXHpy/view?usp=sharing)

[Talkback / Screen Reader Output](https://drive.google.com/file/d/1hsQS9TL6tMDO7lCNMMyKLCMukZjSle1l/view?usp=sharing)

[Signed APK Link](https://drive.google.com/file/d/1nAsedN0CfoKBG_oyvhaaDlLa6j4q2wTS/view?usp=sharing) **4.3 MB Size**

## Test Output
### Roboelectric
<img width="250" alt="Screenshot 2021-04-04 at 9 28 59 PM" src="https://user-images.githubusercontent.com/9396084/113514564-0a42a800-958d-11eb-9b13-0cd18cf2056f.png">

### Espresso
<img width="250" alt="Screenshot 2021-04-04 at 9 27 53 PM" src="https://user-images.githubusercontent.com/9396084/113514563-09aa1180-958d-11eb-8d18-ec5d1e1e39b5.png">

## Notes for Reviewer
- I normally keep UI as simple and dynamic as much possible and therefore I have chosen not to introduce custom animations.
- As per the [Task](https://github.com/rt4914/NASA-Pictures/blob/main/README.md#task) definition I had shown image in full screen which meant that the data can come over the screen which I think is not a good practice if we are targetting accessiblity users too.
- I have introduced `AutoFitGridLayoutManager` which helps to dynamically calculate the span so that the UI can look consistent across devices.
- Used `Gson` library to parse data automatically
- I have also used `DataBinding` in this project.
