
class MyModal extends HTMLElement {
    connectedCallback() {
		//modal frame code
        this.innerHTML = `		    
          <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
              data-bs-keyboard="false" tabindex="-1"
              aria-labelledby="staticBackdropLabel" aria-hidden="true">
              <div class="modal-dialog">
                  <div class="modal-content">
                      <div class="modal-header">
                          <h1 class="modal-title fs-5" id="modal-title">Modal Title</h1>
                          <button type="button" class="btn-close" data-bs-dismiss="modal"
                              aria-label="Close"></button>
                      </div>
                      <div class="modal-body">...</div>
                      <div class="modal-footer">
                          <button type="button" class="btn btn-secondary"
                              data-bs-dismiss="modal">取消</button>
                          <a class="btn btn-danger" id="action-href" href="#">確定</a>
                      </div>
                  </div>
              </div>
          </div>		    
      `;
    }
}

// define <my-modal></my-modal> will be replaced by myModal.innerHTML
customElements.define('my-modal', MyModal);


const dialogModal = document.getElementById('staticBackdrop')
dialogModal.addEventListener('show.bs.modal', event => {
    // Button that triggered the modal
    const button = event.relatedTarget

    // Extract info from data-bs-* attributes
    const href = button.getAttribute('data-bs-action-href')
    const title = button.getAttribute('data-bs-modal-tile')

    // If necessary, you could initiate an AJAX request here
    // and then do the updating in a callback.

    // Update the modal's title	
    const modalTitle = dialogModal.querySelector('#modal-title')
    modalTitle.textContent = title

    // Update the modal's body	
    const modalBody = dialogModal.querySelector('.modal-body')
    modalBody.textContent = '你確定你要' + title + '嗎？'

    // Update the modal's action button	
    const modalAction = dialogModal.querySelector('#action-href')
    modalAction.setAttribute('href', href)

})